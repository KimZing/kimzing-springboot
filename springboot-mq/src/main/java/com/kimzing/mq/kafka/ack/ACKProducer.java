package com.kimzing.mq.kafka.ack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
@RequestMapping("/kafka/ack")
public class ACKProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping
    public void send(){
        kafkaTemplate.send("ack", "message need ack");
    }

}
