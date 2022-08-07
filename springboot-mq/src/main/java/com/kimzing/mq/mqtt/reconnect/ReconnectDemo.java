package com.kimzing.mq.mqtt.reconnect;

import com.kimzing.mq.mqtt.common.MqttConfig;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 16:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt/reconnect")
public class ReconnectDemo {

    private final MqttConfig mqttConfig;

    public void publish() throws MqttException {
        MqttAsyncClient mqttClient = new MqttAsyncClient(mqttConfig.getUrl(), "reconnect_pub", new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());
        options.setCleanSession(false);
        // 异步客户端重连生效
        options.setAutomaticReconnect(true);
        options.setKeepAliveInterval(20);
        options.setMaxReconnectDelay(50000);
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {
                    System.out.println("重连成功");
                    return;
                }
                System.out.println("连接成功");
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("连接丢失");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete");
            }
        });

        mqttClient.connect(options);
    }

    @GetMapping
    public void test() throws MqttException {
        // 连接后断开网络链路，随后等待一分钟再打开网络链路
        publish();
    }

}
