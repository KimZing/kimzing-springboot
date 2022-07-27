package com.kimzing.mq.kafka.partition;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分区配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/26 12:06
 */
@Configuration
public class PartitionConfig {

    @Bean
    public NewTopic partitionTopic () {
        // 会覆盖已存在的topic的配置，这里副本的数量不生效，可能是因为采用的kafka是单机版的
        return new NewTopic("partition", 10, (short) 10);
    }

}
