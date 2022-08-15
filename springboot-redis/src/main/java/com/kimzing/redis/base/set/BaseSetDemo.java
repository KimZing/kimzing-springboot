package com.kimzing.redis.base.set;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/set")
public class BaseSetDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     * @param key
     */
    public void set(String key) {
        RSet<String> set = redissonClient.getSet(key, StringCodec.INSTANCE);
        set.add("a");
        set.add("b");
        set.add("c");
    }

    /**
     * 获取值
     * @param key
     */
    public String get(String key) {
        RSet<String> set = redissonClient.getSet(key, StringCodec.INSTANCE);
        return set.removeRandom();
    }

    @GetMapping("/test")
    public void test(){
        set("base:set");
        System.out.println(get("base:set"));
    }

}
