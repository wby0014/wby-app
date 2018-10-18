package com.example.wby;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
//@EnableJms会启动jms的注解扫描，相当于<jms:annotation-d riven/>
public class ActivemqConfig {


    @Autowired
    private PooledConnectionFactory pooledJmsConnectionFactory;


    @Bean(name = "queueListenerFactory")
    public DefaultJmsListenerContainerFactory queueListenerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(pooledJmsConnectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean(name = "topicListenerFactory")
    public DefaultJmsListenerContainerFactory topicListenerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(pooledJmsConnectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
