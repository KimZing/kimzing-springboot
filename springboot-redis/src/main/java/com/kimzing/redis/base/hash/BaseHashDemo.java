package com.kimzing.redis.base.hash;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/14 17:05
 */
@RestController
@RequestMapping("/base/hash")
public class BaseHashDemo {

    @Resource
    RedissonClient redissonClient;

    /**
     * 设置值
     */
    public void set(String key, Map<String, String> map) {
        RMap<Object, Object> rMap = redissonClient.getMap(key, JsonJacksonCodec.INSTANCE);
        rMap.putAll(map);
    }

    /**
     * 获取值
     */
    public String get(String key, String subKey) {
        RMap<Object, Object> rMap = redissonClient.getMap(key, JsonJacksonCodec.INSTANCE);
        String value = (String) rMap.get(subKey);
        return value;
    }

    @GetMapping("/test")
    public void test(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "kimzing");
        map.put("age", "18");

        set("base:hash", map);
        System.out.println(get("base:hash", "age"));
    }

}
