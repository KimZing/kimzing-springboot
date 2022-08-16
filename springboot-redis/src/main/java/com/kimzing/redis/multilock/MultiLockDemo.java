package com.kimzing.redis.multilock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 使用Redisson实现分布式锁.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/15 21:33
 */
@RestController
@RequestMapping("/lock/multi/redisson")
public class MultiLockDemo {

    @Resource
    RedissonClient redissonClient;

    @Resource
    RedissonClient redissonClient2;

    @GetMapping("/pay")
    public String pay(@RequestParam(required = false, defaultValue = "111") Long orderId) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": 开始支付订单：" + orderId);
        String lockKey = "order:pay:" + orderId;
        RLock lock1 = redissonClient.getLock(lockKey);
        RLock lock2 = redissonClient2.getLock(lockKey);
        RLock multiLock = redissonClient.getMultiLock(lock1, lock2);

        // waitTime是等待时间，而leaseTime则是锁的释放时间，如果大于0那么就是固定的释放时间，不管业务有没有执行完
        // 如果小于0，那么会自动设置30s过期，并且当业务没执行完时自动续期
        if (multiLock.tryLock(1, -1, TimeUnit.SECONDS)) {
            // 此段代码仅为了演示重入锁
            if(multiLock.tryLock()) {
                System.out.println("再次获取锁成功");
                Thread.sleep(40000);
                multiLock.unlock();
            };

            try {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + ": 支付完成：" + orderId);
                return "订单支付完成";
            } finally {
                multiLock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": 正在支付中：" + orderId + "：稍后再试");
            return "订单正在支付, 请稍后再试";
        }

    }
}
