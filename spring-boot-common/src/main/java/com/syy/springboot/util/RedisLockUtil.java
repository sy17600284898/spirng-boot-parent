package com.syy.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author ASUS
 */
@Component
public class RedisLockUtil {

    Logger LOG = LoggerFactory.getLogger(RedisLockUtil.class);
    // 超时时间
    private static final int EXPIRE_TIME = 5 * 1000;

    private static final String SCRIPTTEXT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    @Autowired
    private RedisTemplate redisTemplate;
    private static Map<String, Thread> threadMap = new ConcurrentHashMap();

    public Object lock(String key, Long timeOut) {
        LOG.info("Lock start");
        try {
            // 超时等待时间
            Long waitEnd = System.currentTimeMillis() + EXPIRE_TIME;
            // 生成一个uuid，使得分布式调用有一个拥有者
            String uuid = UUID.randomUUID().toString();
            String value = key + uuid;
            // 在等待时间内，尝试获取锁
            while (System.currentTimeMillis() < waitEnd) {
                LOG.info("Try to acquire lock");
                // 同步代码，使得操作原子性
                synchronized (this) {
                    if (Objects.nonNull(redisTemplate.opsForValue().get(key))) {
                        continue;
                    }
                    Object result = redisTemplate.opsForValue().getAndSet(key, value);
                    if (Objects.isNull(result)) {
                        LOG.info("Successfully acquired lock");
                    }
                    // 设置过期时间，以防死锁
                    redisTemplate.expire(key, timeOut, TimeUnit.MILLISECONDS);
                    // 开启一个守护进程，给当前锁动态添加时间
                    Thread thread = new Thread(() -> {
                        while (true) {
                            try {
                                if (System.currentTimeMillis() > waitEnd) {
                                    System.out.println(Thread.currentThread().getName() + "-->" + " Update redis time 2s ");
                                    redisTemplate.expire(key, 1 * 60000, TimeUnit.MILLISECONDS);
                                    Thread.sleep(1000);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.setDaemon(true);
                    threadMap.put(value, thread);
                    thread.setName(key + "-" + value);
                    thread.start();
                    return value;
                }
            }
        } catch (Exception e) {
            LOG.error("lock error:", e);
            throw new RuntimeException("Failed to acquire distributed lock");
        }
        LOG.info("Failed to acquire lock");
        throw new RuntimeException("Get distributed lock timeout");
    }

    public boolean unLock(String key, Object value) {
        LOG.info("Release lock：{}--{}", key, value);
        if (Objects.isNull(key)) {
            return false;
        }
        DefaultRedisScript script = new DefaultRedisScript();
        script.setResultType(List.class);
        script.setScriptText(SCRIPTTEXT);
        Object o = redisTemplate.execute(script, Collections.singletonList(key), value);
        if (Objects.nonNull(o) && ((ArrayList) o).size() != 0) {
            threadMap.remove(value).stop();
        }
        LOG.info("Release lock{}", o);
        return true;
    }
}