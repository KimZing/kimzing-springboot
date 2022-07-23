package com.kimzing.mq.rabbit.reject;

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
@RabbitListener(queues = "REJECT_QUEUE")
public class RejectConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        System.out.println("收到消息:" + msg);
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
    }

}
