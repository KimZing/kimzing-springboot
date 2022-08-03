package com.kimzing.mq.rocket.delay;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/rocketmq/delay")
public class DelayProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public void send() {
        Message<String> message = MessageBuilder.withPayload("延迟10S的消息：" + LocalDateTime.now()).build();
        rocketMQTemplate.syncSend("delay", message, 1000, 3);
        System.out.println("已发送延迟消息：" + message);
    }
}
