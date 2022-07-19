package com.kimzing.mq.rabbit.limit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/17 21:02
 */
@Configuration
public class LimitMessageConfig {

    /**
     * 交换机
     * @return
     */
    @Bean
    public DirectExchange limitExchange() {
        return new DirectExchange("LIMIT_EXCHANGE", true, false);
    }

    /**
     *
     * @return
     */
    @Bean
    public Queue limitQueue() {
        return new Queue("LIMIT_QUEUE", true, false, false);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding limitBinding() {
        return BindingBuilder.bind(limitQueue()).to(limitExchange()).with("limit");
    }

}
