package com.kimzing.mq.kafka.basic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class BasicConsumer {
    @KafkaListener(topics = "basic",groupId = "basic-group")
    public void onMessage(String msg){
        System.out.println("----basic 收到消息："+msg+"----");
    }
}
