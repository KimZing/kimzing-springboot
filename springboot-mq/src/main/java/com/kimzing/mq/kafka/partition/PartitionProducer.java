package com.kimzing.mq.kafka.partition;

import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Component
@RestController
@RequestMapping("/kafka/partition")
public class PartitionProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/1")
    public void send1(){
        kafkaTemplate.send("partition", 1, "partition key", "message from partition1");
    }

    @GetMapping("/2")
    public void send2(){
        kafkaTemplate.send("partition", 2, "partition key", "message from partition2");
    }

    @GetMapping("/3")
    public void send3(){
        for (int i = 0; i < 100000; i++) {
            kafkaTemplate.send("partition", 3, "partition key", "message from partition3 : " + i);
        }
    }


    @GetMapping("/info")
    public void info() {
        List<PartitionInfo> partition = kafkaTemplate.partitionsFor("partition");
        System.out.println("主题：partition");
        for (int i = 0; i < partition.size(); i++) {
            System.out.println("第" + i + "个分区，分区序号：" + partition.get(i).partition() + "，副本数：" + partition.get(i).replicas().length);
        }

        List<PartitionInfo> partition2 = kafkaTemplate.partitionsFor("basic");
        System.out.println("主题：basic");
        for (int i = 0; i < partition2.size(); i++) {
            System.out.println("第" + i + "个分区，分区序号：" + partition2.get(i).partition() + "，副本数：" + partition2.get(i).inSyncReplicas().length);
        }
    }
}
