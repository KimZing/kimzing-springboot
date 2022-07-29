package com.kimzing.mq.kafka.idempotence;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 幂等生产者.
 * <p>
 *     在producer配置中添加配置 enable.idempotence
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/25 17:33
 */
@RequestMapping("/kafka/idempotence")
@RestController
public class IdempotenceProducer {

    @Resource
    KafkaTemplate<String,Object> kafkaTemplate;

    // 不好演示出来，当broker已经把消息持久化，但没来得及返回ack时挂了，producer再次发送就会触发幂等性
    @GetMapping
    public void send() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            SendResult<String, Object> sendResult = kafkaTemplate.send("idempotence", "same key", "test" + i).get();
        }
    }

}
