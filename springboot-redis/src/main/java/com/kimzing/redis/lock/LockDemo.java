package com.kimzing.redis.lock;

import org.redisson.api.RBucket;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 实现简单版本的分布式锁.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/15 18:12
 */
@RestController
@RequestMapping("/lock/lua")
public class LockDemo {

    @Resource
    RedissonClient redissonClient;

    private static final String LOCK_SCRIPT = "local lockState = redis.call('exists', KEYS[1]) "
            + "if lockState == 1 then "
            + "  return 0 "
            + "else "
            + "  redis.call('set', KEYS[1], ARGV[1]) "
            + "  redis.call('expire', KEYS[1], 30) "
            + "  return 1 "
            + "end";

    @GetMapping("/pay")
    public String pay(@RequestParam(required = false, defaultValue = "111") Long orderId) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": 开始支付订单：" + orderId);
        String lockKey = "order:pay:" + orderId;
        // 标识当前客户端及当前操作线程，在出异常删除锁和解锁时，只有相同客户端的相同线程才可以删除锁
        String lockValue = redissonClient.getId() + ":" + Thread.currentThread().getName();
        try {
            RScript script = redissonClient.getScript();
            Long result = script.eval(RScript.Mode.READ_WRITE, LOCK_SCRIPT, RScript.ReturnType.INTEGER,
                    Arrays.asList(lockKey), lockValue);
            if (result == 1) {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + ": 支付完成：" + orderId);
                return "订单支付完成";
            } else {
                System.out.println(Thread.currentThread().getName() + ": 正在支付中：" + orderId + "：稍后再试");
                return "订单正在支付, 请稍后再试";
            }
        } finally {
            RBucket<String> bucket = redissonClient.getBucket(lockKey);
            String currenLockValue = bucket.get();
            if (lockValue.equals(currenLockValue)) {
                bucket.delete();
                System.out.println(Thread.currentThread().getName() + "：锁信息一致，删除锁");
            } else {
                System.out.println(Thread.currentThread().getName() + "：锁信息不同，不操作");
            }
        }

    }

}
