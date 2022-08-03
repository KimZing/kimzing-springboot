package com.kimzing.mq.rocket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RocketMQApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder rabbit = new SpringApplicationBuilder(RocketMQApplication.class).profiles("rocket");
        rabbit.run(args);
    }

}
