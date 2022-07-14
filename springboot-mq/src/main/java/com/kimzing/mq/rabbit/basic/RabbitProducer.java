package com.kimzing.mq.rabbit.basic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@RequestMapping("/rabbit/basic")
@RestController
public class RabbitProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(1);

    @GetMapping
    public void produce() {
        rabbitTemplate.convertAndSend(  "BASIC_EXCHANGE" ,"kimzing.basic", atomicInteger.getAndIncrement() + " : Hello SpringBoot Rabbit!");
    }

}
