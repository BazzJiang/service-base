package com.jiangkedev.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.time.Duration;

/**
 * @author jiangke
 * @date 16:51
 */
@Slf4j
public class RedisDistributedLock{
    private StringRedisTemplate redisTemplate;
    private ThreadLocal<String> lockFlag = new ThreadLocal<>();
    private static final String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final Long RELEASE_SUCCESS = 1L;
    public RedisDistributedLock(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    public boolean acquire(String lockKey, String requestId, int expireTime){
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            StringRedisConnection  stringRedisConnection = (StringRedisConnection)connection;
            Boolean result = stringRedisConnection.set(lockKey,requestId,Expiration.from(Duration.ofSeconds(expireTime)),RedisStringCommands.SetOption.SET_IF_ABSENT);
            return result;
        });
        return true;
    }
    public boolean release(String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute((RedisCallback<Long>)connection->{
            StringRedisConnection  stringRedisConnection = (StringRedisConnection)connection;
            Long res = stringRedisConnection.eval(script,ReturnType.INTEGER,1,lockKey,requestId);
            return res;
        });
        if(result.equals(RELEASE_SUCCESS)){
            return true;
        }else{
            return false;
        }
    }
}
