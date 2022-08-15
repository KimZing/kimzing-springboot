package com.kimzing.redis.base.set;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/set/focus")
public class FocusDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     * @param key
     */
    public void set(String key, Set<String> fans) {
        RSet<String> set = redissonClient.getSet(key, StringCodec.INSTANCE);
        set.addAll(fans);
    }

    @GetMapping("/test")
    public void test(){
        HashSet<String> kimzingFans = new HashSet<>();
        kimzingFans.add("k1");
        kimzingFans.add("k2");
        kimzingFans.add("k3");
        HashSet<String> timoFans = new HashSet<>();
        timoFans.add("k1");
        timoFans.add("t2");
        timoFans.add("t3");
        set("base:set:kimzing", kimzingFans);
        set("base:set:timo", timoFans);

        // 共同粉丝(交集)
        RSet<String> kimzing = redissonClient.getSet("base:set:kimzing", StringCodec.INSTANCE);
        Set<String> sharedFans = kimzing.readIntersection("base:set:timo");
        System.out.println("共同关注:" + sharedFans);
        // timo可能认识（前一个diff）
        Set<String> mayFans = kimzing.readDiff("base:set:timo");
        System.out.println("timo可能认识的人:" + mayFans);
    }

}
