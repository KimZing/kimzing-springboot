package com.kimzing.mq.kafka.interceptor.config;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 需要在配置文件中配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/26 15:53
 */
public class MyInterceptor implements ProducerInterceptor {

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        System.out.println("拦截器：正在发送消息：" + record.value());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("拦截器：收到ack,已经发送到分区：" + metadata.partition() );
    }

    @Override
    public void close() {
        System.out.println("拦截器：发送者已经关闭");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("拦截器：配置发送器，当前配置：" + configs.toString());
    }
}
