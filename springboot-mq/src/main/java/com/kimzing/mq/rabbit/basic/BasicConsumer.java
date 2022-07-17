package com.kimzing.mq.rabbit.basic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@Component
@RabbitListener(queues = "BASIC_QUEUE")
public class BasicConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        // 回复ACK消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("msg : " + msg);
    }

}
