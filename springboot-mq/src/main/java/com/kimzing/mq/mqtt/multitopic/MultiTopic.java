package com.kimzing.mq.mqtt.multitopic;

import com.kimzing.mq.mqtt.common.MqttFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
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
@RequestMapping("/mqtt/multitopic")
public class MultiTopic {

    private final MqttFactory mqttFactory;

    public void publishMulti() throws MqttException {
        MqttClient client = mqttFactory.createSyncClient("multitopic_pub", true, null, null);
        client.publish("topic/1", "message of topic1".getBytes(), 1, false);
        client.publish("topic/2", "message of topic2".getBytes(), 1, false);
        client.publish("topic/3", "message of topic3".getBytes(), 1, false);
    }

    public void subscribeMultiTopic() throws MqttException {
        IMqttMessageListener listener = (topic, message) -> System.out.println("multi模式收到消息:" + new String(message.getPayload()));
        MqttClient client = mqttFactory.createSyncClient("multitopic_sub1", false, null, null);
        client.subscribe(new String[]{"topic/1", "topic/2", "topic/3"}, new int[]{1, 1, 1}, new IMqttMessageListener[]{
                listener, listener, listener
        });
    }

    public void subscribeFilterTopic() throws MqttException {
        MqttClient client = mqttFactory.createSyncClient("multitopic_sub2", false, null, null);
        client.subscribe("topic/+", (topic, message) -> {
            System.out.println("filter模式收到消息：" + new String(message.getPayload()));
        });
    }

    @GetMapping
    public void test() throws MqttException, InterruptedException {
        subscribeMultiTopic();
        subscribeFilterTopic();
        Thread.sleep(2);
        publishMulti();
    }

}
