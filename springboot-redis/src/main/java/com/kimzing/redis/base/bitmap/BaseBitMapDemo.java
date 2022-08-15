package com.kimzing.redis.base.bitmap;

import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
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
@RequestMapping("/base/bitmap")
public class BaseBitMapDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     *
     * @param key
     * @param index
     */
    public void setTrue(String key, Integer index) {
        RBitSet bitSet = redissonClient.getBitSet(key);
        bitSet.set(index);
    }

    @GetMapping("/test")
    public void test() {
        // 比如某些用户访问了网站，则将id对应的位设置为true
        setTrue("base:bitmap", 12);
        setTrue("base:bitmap", 46);
        setTrue("base:bitmap", 199);
        setTrue("base:bitmap", 43211);
        // 统计共多少用户访问
        RBitSet bitSet = redissonClient.getBitSet("base:bitmap");
        System.out.println("访问用户数：" + bitSet.cardinality());
    }

}
