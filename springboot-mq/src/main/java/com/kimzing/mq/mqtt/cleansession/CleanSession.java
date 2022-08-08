package com.kimzing.mq.mqtt.cleansession;

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
@RequestMapping("/mqtt/cleansession")
public class CleanSession {

    private final MqttFactory mqttFactory;

    private MqttClient publisher = null;
    public void createPublisher() throws MqttException {
        publisher =  mqttFactory.createSyncClient("cleansession_pub", true, null, null);
    }

    public void publish(MqttClient mqttClient) throws MqttException {
        mqttClient.publish("cleansession1", "message with 0".getBytes(), 0, false);
        mqttClient.publish("cleansession1", "message with 1".getBytes(), 1, false);
        mqttClient.publish("cleansession1", "message with 2".getBytes(), 2, false);
    }

    private MqttClient subscriber1 = null;
    public void createSubscribe(String clientId) throws MqttException {
        subscriber1 =  mqttFactory.createSyncClient(clientId, false, null, null);
    }

    public void subscribe(MqttClient subscriber) throws MqttException {
        subscriber.subscribe("cleansession1", 1, (topic, message) -> {
            System.out.println(subscriber.getClientId() + " 收到消息:" + new String(message.getPayload()));
        });
    }

    public void online(MqttClient subscriber) throws MqttException {
        subscriber.reconnect();
    }

    public void offline(MqttClient subscriber) throws MqttException {
        subscriber.disconnect();
    }

    @GetMapping("/test")
    public void testOfflineMessage() throws MqttException, InterruptedException {
            if (subscriber1 == null) {
                createSubscribe("cleansession_sub1");
                System.out.println("订阅者1开始订阅");
                subscribe(subscriber1);
            }

            if (publisher == null) {
                createPublisher();
                System.out.println("发布者发布消息");
                publish(publisher);
                Thread.sleep(1000);
            }

            System.out.println("订阅者1离线");
            offline(subscriber1);
            System.out.println("发布者发布消息");
            publish(publisher);
            Thread.sleep(5000);
            System.out.println("订阅者1上线");
            online(subscriber1);
            System.out.println(String.format("===============测试结束"));
    }
}
