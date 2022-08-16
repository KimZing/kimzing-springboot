package com.kimzing.redis.multilock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/16 14:20
 */
@Configuration
public class RedissonClientConfig {

    @Bean
    public RedissonClient redissonClient2() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://redis.kimzing.com:6379").setPassword("KimZing123456");
        return Redisson.create(config);
    }

    @Bean
    @Primary
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("KimZing123456");;
        return Redisson.create(config);
    }

}
