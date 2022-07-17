package com.kimzing.mq.rabbit.deadletter;

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
public class DeadLetterConfig {

    /**
     * 订单超时交换机，超时的订单会转发到这里
     * @return
     */
    @Bean
    public TopicExchange  orderTimeoutExchange() {
        return new TopicExchange("ORDER_TIMEOUT_EXCHANGE", true, false);
    }

    /**
     * 超时的订单存储队列
     * @return
     */
    @Bean
    public Queue orderTimeoutQueue() {
        return new Queue("ORDER_TIMEOUT_QUEUE", true, false, false);
    }

    /**
     * 绑定超时订单的交换机和队列
     * @return
     */
    @Bean
    public Binding orderTimeoutBinding() {
        // 没有使用Builder，直接创建
        return new Binding("ORDER_TIMEOUT_QUEUE", Binding.DestinationType.QUEUE, "ORDER_TIMEOUT_EXCHANGE", "#", null);
    }

    /**
     * 订单交换机
     * @return
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("ORDER_EXCHANGE", true, false);
    }

    @Bean
    public Queue orderQueue() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", "ORDER_TIMEOUT_EXCHANGE");
        // 绑定死信交换机
        return new Queue("ORDER_QUEUE", true, false, false, params);
    }

    /**
     * 注意其中参数为Exchange时，不会返回binding对象
     * @param orderExchange
     * @param orderQueue
     * @return
     */
    @Bean
    public Binding orderBinding(Exchange orderExchange, Queue orderQueue) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("order").noargs();
    }

}
