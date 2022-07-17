package com.kimzing.mq.rabbit.deadletter;

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
@RequestMapping("/rabbit/dead/letter")
@RestController
public class OrderProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping
    public void produce() {
        String now = LocalDateTime.now().toString();
        MessageProperties properties = new MessageProperties();
        // 十秒
        properties.setExpiration("10000");
        properties.setContentType("text/plain");
        Message message = new Message((now + ": order").getBytes(), properties);
        rabbitTemplate.send("ORDER_EXCHANGE", "order", message);
    }

}
