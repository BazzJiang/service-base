package com.jiangkedev.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author jiangke
 * @date 16:51
 */
@Slf4j
public class RedisDistributedLock extends AbstractDistributedLock{
    private StringRedisTemplate redisTemplate;
    private ThreadLocal<String> lockFlag = new ThreadLocal<>();
    private static final String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    public RedisDistributedLock(StringRedisTemplate redisTemplate){
        super();
        this.redisTemplate = redisTemplate;
    }
    @Override
    public boolean lock(String key,long expire,int retryTime,long sleepMills){
        boolean result = setRedis(key,expire);
        while((!result) && retryTime-- > 0){
            try{
                log.debug("lock faild, retrying..." +  retryTime);
                Thread.sleep(sleepMills);
            }catch (InterruptedException e){
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }
    private boolean setRedis(String key,long expire){
        try{
            redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                StringRedisConnection  stringRedisConnection = (StringRedisConnection)connection;
                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                Boolean result = stringRedisConnection.set(key,uuid,Expiration.from(Duration.ofMillis(expire)),RedisStringCommands.SetOption.SET_IF_ABSENT);
                return result;
            });
        }catch (Exception e){
            log.error("set redis occured an exception:{}",e.getMessage());
        }
        return false;
    }
    @Override
    public boolean releaseLock(String key){
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try{
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(lockFlag.get());
            List<String> keysAndArgs = new ArrayList<>();
            keysAndArgs.addAll(keys);
            keysAndArgs.addAll(args);
            Long result = redisTemplate.execute((RedisCallback<Long>)connection->{
                StringRedisConnection  stringRedisConnection = (StringRedisConnection)connection;
                Long res = stringRedisConnection.eval(script,ReturnType.INTEGER,keys.size(), keysAndArgs.toArray(new String[keysAndArgs.size()]));
                return res;
            });
            return result > 0;
        }catch (Exception e){
            log.error("release lock occured an exception {}", e.getMessage());
        }
        return false;
    }
}
