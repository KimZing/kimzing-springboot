package com.kimzing.mq.kafka.serializer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 拦截器演示.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/26 15:52
 */
@RestController
@RequestMapping("/kafka/serializer")
public class SerializerProducer {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public void send(){
        kafkaTemplate.send("serializer", "serializer message");
    }

}
