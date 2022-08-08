package com.kimzing.mq.mqtt.retain;

import com.kimzing.mq.mqtt.common.MqttFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 16:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt/retain")
public class RetainDemo {

    private final MqttFactory mqttFactory;

    public void publish() throws MqttException {
        MqttClient client = mqttFactory.createSyncClient("retain", false, null, null);
        client.publish("retain", "保留消息".getBytes(), 1, true);
    }

    public MqttClient subscribe() throws MqttException {
        MqttClient client = mqttFactory.createSyncClient("retain_sub", false, null, null);
        client.subscribe("retain", (topic, message) -> {
            System.out.println("收到消息：" + new String(message.getPayload()));
        });
        return client;
    }

    MqttClient sameClient = null;
    MqttClient publisher = null;
    @GetMapping("/same")
    public void testSameClient() throws MqttException, InterruptedException {
        if (sameClient == null) {
            sameClient = subscribe();
        }
        if (publisher == null) {
            publish(); // 第一次测试时打开，发送一条保留消息
        }
        Thread.sleep(1000);
        System.out.println("断开连接");
        sameClient.disconnect();
        sameClient.reconnect();
        System.out.println("重连成功");
        Thread.sleep(1000);
        System.out.println("断开连接");
        sameClient.disconnect();
        sameClient.reconnect();
        System.out.println("重连成功");
    }

    @GetMapping("/new")
    public void testNewClient() throws MqttException, InterruptedException {
        subscribe();
        if (publisher == null) {
            publish(); // 第一次测试时打开，发送一条保留消息
        }
        Thread.sleep(3);
        System.out.println("创建新的client");
        subscribe();
        System.out.println("创建新的client");
        subscribe();
    }

}
