package com.kimzing.redis.base.string;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 演示缓存.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:34
 */
@RestController
@RequestMapping("/base/string/cache")
public class CacheDemo {

    @Resource
    RedissonClient redissonClient;

    @GetMapping("/test/nocache")
    public String testNoCache() {
        long start = System.currentTimeMillis();
        String result = work();
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        return result;
    }

    @GetMapping("/test/usecache")
    public String testCache() {
        long start = System.currentTimeMillis();
        RBucket<String> bucket = redissonClient.getBucket("base:cache");
        String cache = bucket.get();
        if (cache != null) {
            System.out.println("耗时:" + (System.currentTimeMillis() - start));
            return cache;
        }
        String result = work();
        bucket.set(result, 1, TimeUnit.MINUTES);
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        return result;
    }

    public String work() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }

}
