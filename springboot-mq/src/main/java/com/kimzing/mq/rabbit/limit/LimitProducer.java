package com.kimzing.mq.rabbit.limit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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
@RequestMapping("/rabbit/limit")
@RestController
public class LimitProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping
    public void produce() {
        MessageProperties properties = new MessageProperties();
        properties.setContentType("text/plain");
        for (int i = 0; i < 100; i++) {
            Message message = new Message(String.format("消息编号:%d", i).getBytes(), properties);
            rabbitTemplate.send("LIMIT_EXCHANGE", "limit", message);
        }
    }

}
