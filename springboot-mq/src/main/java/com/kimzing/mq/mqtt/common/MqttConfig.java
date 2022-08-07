package com.kimzing.mq.mqtt.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 12:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {

    private String url;

    private String username;

    private String password;

}
