package com.kimzing.mq.kafka.ack;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Component
public class ACKConsumer {
    /**
     * @param msg
     */
    @KafkaListener(topics = {"ack"}, groupId = "ack-group")
    public void onMessage(String msg, Acknowledgment acknowledgment) {
            System.out.println("----ack 收到消息：" + msg + "----");
            acknowledgment.acknowledge();
    }
}
