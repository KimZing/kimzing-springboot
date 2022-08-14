package com.kimzing.redis.base.list;

import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 与双向队列的特征一致，且能保证顺序.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/list")
public class BaseListDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     */
    public void set(String key) {
        RDeque<Object> deque = redissonClient.getDeque(key, StringCodec.INSTANCE);
        deque.addFirst("周一");
        deque.addFirst("周二");
        deque.addFirst("周三");
        deque.addFirst("周四");
    }

    /**
     * 获取值
     */
    public String get(String key) {
        RDeque<Object> deque = redissonClient.getDeque(key, StringCodec.INSTANCE);
        return (String) deque.pollLast();
    }

    @GetMapping("/test")
    public void test(){
        set("base:list");
        System.out.println(get("base:list"));
        System.out.println(get("base:list"));
        System.out.println(get("base:list"));
        System.out.println(get("base:list"));
    }

}
