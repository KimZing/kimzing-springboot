package com.kimzing.mq.rocket.selector;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Component
@RestController
@RequestMapping("/rocketmq/selector")
public class SelectorProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public void send() {
        // 这里貌似是不生效的，通过源码可以看到只有在Orderly，sendOneway等几个方法中使用到了
        rocketMQTemplate.setMessageQueueSelector((mqs, msg, arg) -> mqs.get(0));
        for (int i = 0; i < 10; i++) {
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("selector", "selector message", null, 10000);
            System.out.println(sendResult);
        }
    }

}
