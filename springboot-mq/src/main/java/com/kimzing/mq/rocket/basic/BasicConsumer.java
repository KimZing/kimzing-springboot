package com.kimzing.mq.rocket.basic;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(topic = "basic", consumerGroup = "basic-group", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.CONCURRENTLY)
public class BasicConsumer  implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("basic consumer 收到消息:" + message);
    }
}
