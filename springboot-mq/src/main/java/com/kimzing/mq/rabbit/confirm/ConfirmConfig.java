package com.kimzing.mq.rabbit.confirm;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:40
 */
@Configuration
public class ConfirmConfig {

    // Exchange
    @Bean("confirmExchange")
    public Exchange createExchange(){
        return new DirectExchange("CONFIRM_EXCHANGE", true, false);
    }

    // Queue
    @Bean("confirmQueue")
    public Queue createQueue() {
        return new Queue("CONFIRM_QUEUE", true, false, false);
    }

    // 绑定
    @Bean
    public Binding confirmBinding() {
        return BindingBuilder.bind(createQueue()).to(createExchange()).with("confirm").noargs();
    }

}
