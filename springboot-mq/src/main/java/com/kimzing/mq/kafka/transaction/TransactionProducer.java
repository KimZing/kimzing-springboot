package com.kimzing.mq.kafka.transaction;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 事物演示.
 * <p>
 *     需添加transaction-id-prefix属性，该属性不为空时，则开启事物
 *     可以通过
 * </p>
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/7/25 18:02
 */
@RequestMapping("/kafka/transaction")
@RestController
public class TransactionProducer {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送多条消息一个失败即全部取消
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/multi/message")
    public void sendMultiMessage() {
        kafkaTemplate.send("transaction", "1");
        kafkaTemplate.send("transaction", "2");
        int a = 1 / 0 ;
        kafkaTemplate.send("transaction", "3");
    }

    /**
     * 发送到不同topic
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/multi/topic")
    public void sendMultiTopic() {
        kafkaTemplate.send("transaction1", "1");
        kafkaTemplate.send("transaction2", "2");
        int a = 1 / 0 ;
        kafkaTemplate.send("transaction2", "3");
    }




    /**
     * 接收一个消息并发送下一条消息，如果失败那么这个消息的消费也应该失败
     * <p>
     *     注意事物要加在这里
     * </p>
     */
    @GetMapping("/consumer/producer")
    @Transactional(rollbackFor = Exception.class)
    public void start() {
        kafkaTemplate.send("transaction3", "开始发送消息");
    }

    /**
     *  注意设置enable-auto-commit: false, 这样不会自动提交offset，消费失败达到最大重试次数时就会停止消费，下次重启仍然继续该offset消费
     * @param msg
     */
    @KafkaListener(topics = {"transaction3"}, groupId = "transaction3-group")
    public void consumeAndProducer(String msg) {
        System.out.println("接收到消息:" + msg + " 准备发送消息");
        kafkaTemplate.send("transaction4", "消费完之后发送的消息是否能收到");
        int a = 9 / 0;
    }

}
