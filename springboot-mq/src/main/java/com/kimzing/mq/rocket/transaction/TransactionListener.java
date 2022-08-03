package com.kimzing.mq.rocket.transaction;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2022/8/2 17:49
 */
@RocketMQTransactionListener(txProducerGroup = "transaction-group")
public class TransactionListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(org.springframework.messaging.Message msg, Object arg) {
        System.out.println("执行本地事物" + new String((byte[]) msg.getPayload()));
        if ((new String((byte[]) msg.getPayload()).contains("success"))) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(org.springframework.messaging.Message msg) {
        System.out.println("检查本地事物" + new String((byte[]) msg.getPayload()));
        // 根据情况决定提交还是回滚
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
