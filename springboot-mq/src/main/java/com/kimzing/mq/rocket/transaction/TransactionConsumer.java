package com.kimzing.mq.rocket.transaction;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(topic = "transaction", consumerGroup = "transaction-group", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.CONCURRENTLY)
public class TransactionConsumer implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("transaction consumer 收到消息:" + message);
    }
}
