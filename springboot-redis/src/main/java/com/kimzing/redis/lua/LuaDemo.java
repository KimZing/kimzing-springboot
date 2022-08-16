package com.kimzing.redis.lua;

import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/15 15:11
 */
@RestController
@RequestMapping("/lua")
public class LuaDemo {

    @Resource
    RedissonClient redissonClient;

    @GetMapping("/test")
    public void test() {
        RScript script = redissonClient.getScript(StringCodec.INSTANCE);
        Object result = script.eval(RScript.Mode.READ_WRITE,
                "local exists = redis.call('exists', KEYS[1]) if exists == 1 then return '值已存在' else redis.call('set', KEYS[1], ARGV[1]) return '设置成功' end",
                RScript.ReturnType.VALUE, Arrays.asList("lua"), "kimzing");
        System.out.println(result);
    }

}
