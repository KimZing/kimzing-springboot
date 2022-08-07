package com.kimzing.mq.mqtt.lastwill;

import com.kimzing.mq.mqtt.common.MqttFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试遗嘱的发送在正常断开与非正常断开时的表现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/6 16:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt/lastwill")
public class LastWillDemo {

    private final MqttFactory mqttFactory;

    public MqttClient createClient() throws MqttException {
        return mqttFactory.createSyncClient("lastwill", false, "die", "i am offline");
    }

    public void subscribeWillTopic() throws MqttException {
        // 再创建一个监听遗嘱消息的客户端
        MqttClient willClient = mqttFactory.createSyncClient("die_subscribe", true, null, null);
        willClient.subscribe("die", (topic, message) -> System.out.println("收到遗嘱消息" + new String(message.getPayload())));
    }

    public void shutdownNormal(MqttClient mqttClient) throws MqttException {
        mqttClient.disconnect();
    }

    @GetMapping("/normal")
    public void testNormalShutdown() throws MqttException {
        subscribeWillTopic();
        MqttClient client = createClient();
        shutdownNormal(client);
    }

    @GetMapping("/force")
    public void testForcelShutdown() throws MqttException {
        subscribeWillTopic();
        createClient();
        // 通过强制关闭程序模拟异常下线，然后再次启动就会收到遗嘱消息
    }

}
