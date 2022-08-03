package com.kimzing.mq.rocket.tag;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(topic = "tag", consumerGroup = "tag-group", selectorExpression = "create",
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.CONCURRENTLY)
public class CreateTagConsumer implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("create consumer 收到消息:" + message);
    }
}
