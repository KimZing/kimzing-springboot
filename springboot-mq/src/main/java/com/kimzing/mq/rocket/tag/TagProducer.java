package com.kimzing.mq.rocket.tag;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Component
@RestController
@RequestMapping("/rocketmq/tag")
public class TagProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public void send() {
        SendResult sendResult = rocketMQTemplate.syncSend("tag:create", "basic create message ", 10000);
        System.out.println("已发送消息：" + sendResult.toString());
        SendResult sendResult2 = rocketMQTemplate.syncSend("tag:update", "basic update message ", 10000);
        System.out.println("已发送消息：" + sendResult2.toString());
    }

}
