package com.kimzing.mq.rabbit.deadletter;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 消费者.
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@Component
@RabbitListener(queues = "ORDER_TIMEOUT_QUEUE")
public class TimeoutOrderConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

        String now = LocalDateTime.now().toString();
        System.out.println("now:" + now + " msg : " + msg);
    }

}
