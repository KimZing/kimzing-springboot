package com.kimzing.mq.rabbit.transaction;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/rabbit/transaction")
public class TransactionProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping
    @Transactional
    public void send(){
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend("TRANSACTION_EXCHANGE", "transaction", "test transaction");
        // int i = 5 / 0;
    }

}
