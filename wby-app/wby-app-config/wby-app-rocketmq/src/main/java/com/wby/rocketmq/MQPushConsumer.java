package com.wby.rocketmq;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wubinyu
 * @date 2019/9/20 14:37.
 */
//@PropertySource("classpath:test.properties")
@Slf4j
@Service
public class MQPushConsumer implements MessageListenerConcurrently {

    @Value("${spring.rocketmq.namesrvaddr}")
    private String namesrvAddr;

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestRocketMQPushConsumer");

    @PostConstruct
    public void start() {
        try {
            log.info("MQ: 启动消费者");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.subscribe("TopicTest", "*");
            consumer.registerMessageListener(this::consumeMessage);
            consumer.start();
        } catch (MQClientException e) {
            log.error("MQ: 启动消费者失败：{}-{}", e.getResponseCode(), e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        int index = 0;
        try {
            for (; index < list.size(); index++) {
                MessageExt msg = list.get(index);
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                log.info("MQ: 消费者接收新消息：{} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (index < list.size()) {
                context.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            log.error("MQ: 关闭消费者");
        }
    }
}
