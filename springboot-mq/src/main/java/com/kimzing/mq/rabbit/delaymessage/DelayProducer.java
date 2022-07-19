package com.kimzing.mq.rabbit.delaymessage;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 生产者.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@RequestMapping("/rabbit/delay/message")
@RestController
public class DelayProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping
    public void produce() {
        String sendTime = LocalDateTime.now().toString();
        String receiveTime = LocalDateTime.now().plusSeconds(10).toString();
        MessageProperties properties = new MessageProperties();
        properties.setHeader("x-delay", 10000);
        properties.setContentType("text/plain");
        Message message = new Message(String.format("发送时间:%s, 预计接收时间: %s", sendTime, receiveTime).getBytes(), properties);
        rabbitTemplate.send("DELAY_EXCHANGE", "delay.message", message);
    }

}
