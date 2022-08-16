package com.kimzing.redis.pipeline;

import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RBucketAsync;
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
 * @since 2022/8/15 14:17
 */
@RestController
@RequestMapping("/pipeline")
public class PiplineDemo {

    @Resource
    RedissonClient redissonClient;

    @GetMapping("/test")
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            RBucket<Object> bucket = redissonClient.getBucket("single:" + i, StringCodec.INSTANCE);
            bucket.set(i);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < 100000; i++) {
            RBucketAsync<Object> bucket = batch.getBucket("multi:" + i);
            bucket.setAsync(i);
        }
        batch.execute();
        System.out.println(System.currentTimeMillis() - start);
    }

}
