package com.wby.rocketmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wubinyu
 * @date 2019/9/20 15:09.
 */
@SpringBootApplication
public class RocketMQApplication {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>>>> rocketmq application 正在启动 <<<<<<<");
        SpringApplication.run(RocketMQApplication.class, args);
        logger.info(">>>>>>> rocketmq application 启动完成 <<<<<<<");
    }

}
