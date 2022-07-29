package com.kimzing.mq.kafka.idempotence;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class IdempotenceConsumer {
    /**
     * @param msg
     */
    @KafkaListener(topics = {"idempotence"}, groupId = "idempotence-group")
    public void onMessage(String msg) {
        System.out.println("----idempotence 收到消息：" + msg + "----");
    }
}
