package com.kimzing.mq.rocket.order;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Component
@RestController
@RequestMapping("/rocketmq/order")
public class OrderProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public void send() {
        // hashkey保持一致，通过selector就会选择一个队列
        SendResult sendResult = rocketMQTemplate.syncSendOrderly("order", "创建订单", "order id 1", 10000);
        System.out.println("已发送消息：" + sendResult.toString());
        SendResult sendResult2 = rocketMQTemplate.syncSendOrderly("order", "付款", "order id 1", 10000);
        System.out.println("已发送消息：" + sendResult2.toString());
        SendResult sendResult3 = rocketMQTemplate.syncSendOrderly("order", "取消订单", "order id 1", 10000);
        System.out.println("已发送消息：" + sendResult3.toString());
    }

}
