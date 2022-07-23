package com.kimzing.mq.kafka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KafkaMQApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder rabbit = new SpringApplicationBuilder(KafkaMQApplication.class).profiles("kafka");
        rabbit.run(args);
    }

}
