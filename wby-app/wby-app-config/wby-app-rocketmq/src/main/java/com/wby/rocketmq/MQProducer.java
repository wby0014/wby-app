package com.wby.rocketmq;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wubinyu
 * @date 2019/9/20 10:59.
 */
//@PropertySource("classpath:test.properties")
@Slf4j
@Service
public class MQProducer {

    @Value("${spring.rocketmq.namesrvaddr}")
    private String namesrvAddr;

    private final DefaultMQProducer producer = new DefaultMQProducer("TestRocketMQProducer");

    @PostConstruct
    public void start() {
        try {
            log.info("MQ: 启动生产者");
            producer.setNamesrvAddr(namesrvAddr);
            producer.start();
        } catch (MQClientException e) {
            log.error("MQ: 启动生产者失败：{}-{}", e.getResponseCode(), e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void sendMessage(String data, String topic, String tags, String keys) {
        try {
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message mqMsg = new Message(topic, tags, keys, messageBody);
            producer.send(mqMsg, new SendCallback() {
                @Override public void onSuccess(SendResult sendResult) {
                    log.info("MQ: 生产者发送消息 {}", sendResult);
                }

                @Override public void onException(Throwable throwable) {
                    log.error(throwable.getMessage(), throwable);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void stop() {
        if (producer != null) {
            producer.shutdown();
            log.info("MQ: 关闭生产者");
        }
    }
}
