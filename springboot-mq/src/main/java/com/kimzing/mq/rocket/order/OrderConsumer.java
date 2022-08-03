package com.kimzing.mq.rocket.order;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(topic = "order", consumerGroup = "order-group", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class OrderConsumer implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("order consumer 收到消息:" + message);
    }
}
