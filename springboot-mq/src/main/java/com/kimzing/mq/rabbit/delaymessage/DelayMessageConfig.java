package com.kimzing.mq.rabbit.delaymessage;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/17 21:02
 */
@Configuration
public class DelayMessageConfig {

    /**
     * 自定义定义带有延时功能的交换机
     * @return
     */
    @Bean
    public CustomExchange  delayExchange() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "topic");
        return new CustomExchange("DELAY_EXCHANGE", "x-delayed-message", true, false, args);
    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue delayQueue() {
        return new Queue("DELAY_QUEUE", true, false, false);
    }

    /**
     * 绑定延时交换机和队列
     * @return
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("#").noargs();
    }

}
