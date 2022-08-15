package com.kimzing.redis.base.zset;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/zset")
public class BaseZSetDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     * @param key
     */
    public void set(String key) {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(key, StringCodec.INSTANCE);
        scoredSortedSet.add(3.3, "苹果");
        scoredSortedSet.add(3.0, "西瓜");
        scoredSortedSet.add(23.0, "榴莲");
        scoredSortedSet.add(6.0, "阳光玫瑰");
    }

    @GetMapping("/test")
    public void test(){
        set("base:zset");
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet("base:zset", StringCodec.INSTANCE);
        // 查询榴莲分数(价格)
        System.out.println(scoredSortedSet.getScore("榴莲"));
        // 给西瓜涨价(加分)
        scoredSortedSet.addScore("西瓜", 1);
        // 根据价格排序
        Collection<ScoredEntry<Object>> fruits = scoredSortedSet.entryRangeReversed(0, -1);
        fruits.stream().forEach(o -> System.out.println(o.getValue()));
        // 获取最贵的两种水果
        Collection<ScoredEntry<Object>> scoredEntries = scoredSortedSet.entryRangeReversed(0, 1);
        scoredEntries.stream().forEach(o -> System.out.println(o.getValue()));
    }

}
