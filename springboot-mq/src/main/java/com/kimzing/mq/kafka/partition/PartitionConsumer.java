package com.kimzing.mq.kafka.partition;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;


@Component
public class PartitionConsumer {

    @KafkaListener(groupId = "partition-group", topicPartitions = @TopicPartition(topic = "partition", partitions = "1"))
    public void onMessage1(String msg) {
        System.out.println("----consumer1 收到消息：" + msg + "----");
    }

    /**
     * 可以通过@PartitionOffset自定义从哪个位置开始消费
     * @param msg
     */
    @KafkaListener(groupId = "partition-group", topicPartitions = @TopicPartition(topic = "partition",
            partitionOffsets = @PartitionOffset(partition = "2", initialOffset = "0")))
    public void onMessage2(String msg) {
        System.out.println("----consumer2 收到消息：" + msg + "----");
    }

}
