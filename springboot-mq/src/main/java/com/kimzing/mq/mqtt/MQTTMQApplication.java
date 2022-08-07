package com.kimzing.mq.mqtt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MQTTMQApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder rabbit = new SpringApplicationBuilder(MQTTMQApplication.class).profiles("mqtt");
        rabbit.run(args);
    }

}
