package com.kimzing.redis.base.list;

import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 演示作为消息队列使用.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/list")
public class BlockingQueueDemo {

    @Resource
    RedissonClient redissonClient;

    Integer number = 1;

    /**
     * 设置值
     */
    @GetMapping("/publish")
    public void set() {
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("base:list:blocking", StringCodec.INSTANCE);
        blockingDeque.offer(number++);
    }

    /**
     * 获取值
     */
    @GetMapping("/subscribe")
    public void get() throws InterruptedException {
        while (true) {
            RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("base:list:blocking", StringCodec.INSTANCE);
            System.out.println("准备接收消息");
            String value  = (String) blockingDeque.pollLast(1000, TimeUnit.SECONDS);
            System.out.println("收到消息: " + value);
        }
    }

}
