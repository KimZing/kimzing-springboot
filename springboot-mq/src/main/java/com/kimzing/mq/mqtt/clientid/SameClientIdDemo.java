package com.kimzing.mq.mqtt.clientid;

import com.kimzing.mq.mqtt.common.MqttFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试相同ClientId先后连接后，哪个client可以正常交互.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 16:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt/clientId")
public class SameClientIdDemo {
    private final MqttFactory mqttFactory;

    public void produce(Integer times) throws MqttException {
        MqttClient client = mqttFactory.createSyncClient("same_id_publisher", false, null, null);
        client.publish("same_client_id", new MqttMessage(("第" + times + "消息").getBytes()));
    }

    public void consume(String clientId, String name) throws MqttException {
        MqttClient client = mqttFactory.createSyncClient(clientId, false, null, null);
        client.subscribe("same_client_id", (topic, message) -> {
            System.out.println(name + "接收到消息:" + new String(message.getPayload()));
        });
    }

    @GetMapping
    public void test() throws MqttException, InterruptedException {
        // 启动第一个客户端并订阅
        consume("same_id", "consumer1");
        // 发送消息
        produce(1);
        // 睡眠5s之后启动第二个客户端
        Thread.sleep(5000);
        // 启动第二个客户端并订阅
        consume("same_id", "consumer2");
        // 发送消息
        produce(2);
    }

}
