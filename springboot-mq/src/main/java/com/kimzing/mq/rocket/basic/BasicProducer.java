package com.kimzing.mq.rocket.basic;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/rocketmq/basic")
public class BasicProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public void send(){
        for (int i = 0; i < 6; i++) {
            SendResult sendResult = rocketMQTemplate.syncSend("basic:normal", "basic 消息 " + i, 10000);
            System.out.println("已发送消息：" + sendResult.toString());
        }
    }

}
