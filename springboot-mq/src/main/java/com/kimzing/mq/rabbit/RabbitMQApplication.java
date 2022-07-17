package com.kimzing.mq.rabbit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RabbitMQApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder rabbit = new SpringApplicationBuilder(RabbitMQApplication.class).profiles("rabbit");
        rabbit.run(args);
    }

}
