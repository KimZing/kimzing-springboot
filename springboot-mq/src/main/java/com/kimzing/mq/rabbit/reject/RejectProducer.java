package com.kimzing.mq.rabbit.reject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 生产者.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@RequestMapping("/rabbit/reject")
@RestController
public class RejectProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping
    public void produce() {
        rabbitTemplate.convertAndSend(  "REJECT_EXCHANGE" ,"reject", "reject message!");
    }

}
