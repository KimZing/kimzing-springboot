package com.kimzing.mq.kafka.serializer.config;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;

/**
 * 需要在配置文件中配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/26 15:53
 */
public class MyStringSerializer implements Serializer<String> {

    @Override
    public byte[] serialize(String topic, String data) {
        return data.getBytes(Charset.forName("utf-8"));
    }
}
