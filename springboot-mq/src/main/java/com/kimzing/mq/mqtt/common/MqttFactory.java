package com.kimzing.mq.mqtt.common;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

/**
 * 客户端工厂.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 12:17
 */
@Component
@RequiredArgsConstructor
public class MqttFactory {

    private final MqttConfig mqttConfig;

    public MqttClient createSyncClient(String clientId, Boolean cleanSession, String willTopic, String willPayload) throws MqttException {
        MqttClient mqttClient = new MqttClient(mqttConfig.getUrl(), clientId, new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());
        options.setCleanSession(cleanSession);
        if (StringUtils.isNotBlank(willTopic)) {
            options.setWill(willTopic, willPayload.getBytes(), 1, true);
        }

        // 同步客户端设置重连(无效)
        // options.setAutomaticReconnect(true);
        // options.setMaxReconnectDelay(50000);

        options.setKeepAliveInterval(35);

        mqttClient.connect(options);
        return mqttClient;
    }

}
