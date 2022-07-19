package com.kimzing.mq.rabbit.limit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消费者.
 * concurrency 指定同时接收请求的并发
 *
 * 1. 设置为手动确认消息MANUAL，然后设置QOS，当没有进行ack时，不会发送吓一条消息。
 * 2. springboot中是在配置文件配置：spring.rabbitmq.listener.simple.prefetch: 1
 * 3. 这个prefech是指拿一批数据，比如此时concurrency设置为20个，那么就是一次拿20个
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:39
 */
@Component
@RabbitListener(queues = "LIMIT_QUEUE", ackMode = "MANUAL", concurrency = "20")
public class LimitConsumer {

    @RabbitHandler()
    public void process(String msg, Channel channel, Message message) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(" msg : " + msg);
    }

}
