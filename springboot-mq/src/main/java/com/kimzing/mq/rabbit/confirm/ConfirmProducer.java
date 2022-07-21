package com.kimzing.mq.rabbit.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/21 14:48
 */
@RestController
@RequestMapping("/rabbit/confirm/standard")
public class ConfirmProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 注意需要开启回调功能
     *     # 确认回调，消息从 producer 到 exchange 则会返回一个 confirmCallback
     *     spring.rabbitmq.publisher-confirm-type: correlated
     *     # 入队失败回调，消息从 exchange–>queue 投递失败则会返回一个 returnCallback
     *     spring.rabbitmq.publisher-returns: true
     */
    @GetMapping
    public void send(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
        // 交换机正常接收，入队成功，仅会回调confirm
        rabbitTemplate.send("CONFIRM_EXCHANGE", "confirm", new Message("确认".getBytes()), new CorrelationData("exchange success"));

        // 交换机正常，入队失败，confirm都会回调
        rabbitTemplate.send("CONFIRM_EXCHANGE", "confirm2", new Message("确认".getBytes()), new CorrelationData("exchange success, queue fail"));

        // 交换机就失败，仅会回调confirm
        rabbitTemplate.send("CONFIRM_EXCHANGE2", "confirm2", new Message("确认".getBytes()), new CorrelationData("exchange fail, queue fail"));
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (correlationData != null) {
            System.out.println("关联信息:"+ correlationData.getId() + ",交换机回调:"  + ack);
        } else {
            System.out.println("交换机回调:"  + ack);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("入队失败回调" + returned.toString());
    }
}
