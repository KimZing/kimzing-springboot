package com.kimzing.mq.rabbit.transaction;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/14 17:40
 */
@Configuration
public class TransactionConfig {

    // Exchange
    @Bean("transactionExchange")
    public TopicExchange createExchange(){
        return new TopicExchange("TRANSACTION_EXCHANGE", true, false);
    }

    // Queue
    @Bean("transactionQueue")
    public Queue createQueue() {
        return new Queue("TRANSACTION_QUEUE", true, false, false);
    }

    // 绑定
    @Bean
    public Binding transactionBinding() {
        return BindingBuilder.bind(createQueue()).to(createExchange()).with("#.transaction");
    }

    /**
     * 事务管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    RabbitTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

}
