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

    // 没演示出来
    @GetMapping
    public void send() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            SendResult<String, Object> sendResult = kafkaTemplate.send("basic", "test").get();
            System.out.println(sendResult);
        }
    }

}
