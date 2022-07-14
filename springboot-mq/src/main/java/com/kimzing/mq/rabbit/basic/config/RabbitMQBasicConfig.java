package com.kimzing.mq.rabbit.basic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:40
 */
@Profile("rabbit-basic")
@Configuration
public class RabbitMQBasicConfig {

    // Exchange
    @Bean("basicExchange")
    public TopicExchange createTopicExchange(){
        return new TopicExchange("BASIC_EXCHANGE", true, false);
    }

    // Queue
    @Bean("basicQueue")
    public Queue createQueue() {
        return new Queue("BASIC_QUEUE", true, false, false);
    }

    // 绑定
    @Bean
    public Binding binding(TopicExchange basicExchange, Queue basicQueue) {
        return BindingBuilder.bind(basicQueue).to(basicExchange).with("*.basic");
    }

}
