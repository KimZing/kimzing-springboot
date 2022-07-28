package com.kimzing.mq.kafka.basic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class BasicConsumer {
    /**
     * 一个消费者组可以同时消费多个topic
     * @param msg
     */
    @KafkaListener(topics = {"basic", "basic2"},groupId = "basic-group")
    public void onMessage(String msg){
        System.out.println("----basic 收到消息："+msg+"----");
    }
}
