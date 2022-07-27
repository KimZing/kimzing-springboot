package com.kimzing.mq.kafka.interceptor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/kafka/interceptor")
public class InterceptorProducer {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public void send(){
        kafkaTemplate.send("interceptor", "interceptor message");
    }

}
