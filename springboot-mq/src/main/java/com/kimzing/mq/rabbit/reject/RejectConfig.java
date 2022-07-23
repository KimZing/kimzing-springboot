package com.kimzing.mq.rabbit.reject;

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
public class RejectConfig {

    // Exchange
    @Bean("rejectExchange")
    public DirectExchange createExchange(){
        return new DirectExchange("REJECT_EXCHANGE", true, false);
    }

    // Queue
    @Bean("rejectQueue")
    public Queue createQueue() {
        return new Queue("REJECT_QUEUE", true, false, false);
    }

    // 绑定
    @Bean
    public Binding rejectBinding() {
        return BindingBuilder.bind(createQueue()).to(createExchange()).with("reject");
    }

}
