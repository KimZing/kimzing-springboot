package com.kimzing.mq.rocket.delay;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RocketMQMessageListener(topic = "delay", consumerGroup = "delay-group", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.CONCURRENTLY)
public class DelayConsumer implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println(LocalDateTime.now() + " : delay 收到消息:" + message);
    }
}
