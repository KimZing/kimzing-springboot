package com.kimzing.mq.rocket.transaction;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Component
@RestController
@RequestMapping("/rocketmq/transaction")
public class TransactionProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("/success")
    public void sendSuccess(){
        rocketMQTemplate.sendMessageInTransaction("transaction-group", "transaction",
                MessageBuilder.withPayload("transaction message success").build(), "test");
    }

    @GetMapping("/fail")
    public void sendFail(){
        rocketMQTemplate.sendMessageInTransaction("transaction-group", "transaction",
                MessageBuilder.withPayload("transaction message fail").build(), "test");
    }

}
