package com.kimzing.redis.base.string;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/string")
public class BaseDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        RBucket<Object> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, 1, TimeUnit.MINUTES);
    }

    /**
     * 获取值
     * @param key
     */
    public String getString(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }

    @GetMapping("/test")
    public void test(){
        setString("base:string", "hello string");
        System.out.println(getString("base:string"));
    }

}
