package com.kimzing.mq.kafka.transaction;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class TransactionConsumer {

    @KafkaListener(topics = "transaction1", groupId = "transaction-group1")
    public void onMessage1(String msg) {
        System.out.println("----transaction1 收到消息：" + msg + "----");
    }

    @KafkaListener(topics = "transaction2", groupId = "transaction-group2")
    public void onMessage2(String msg) {
        System.out.println("----transaction2 收到消息：" + msg + "----");
    }

    @KafkaListener(topics = "transaction4", groupId = "transaction-group4")
    public void onMessage4(String msg) {
        System.out.println("----transaction4 收到消息：" + msg + "----");
    }
}
