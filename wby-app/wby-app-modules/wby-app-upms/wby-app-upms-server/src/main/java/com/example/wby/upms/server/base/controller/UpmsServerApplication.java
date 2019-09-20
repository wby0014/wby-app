package com.example.wby.upms.server.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wubinyu
 * @date 2018/10/18 18:03.
 */
@ComponentScan(basePackages= {"com.example"})
@ImportResource({"classpath:dubbo-consumer.xml"})
//@MapperScan(basePackages= {"com.example.wby.**.mapper"})
@SpringBootApplication
public class UpmsServerApplication extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(UpmsServerApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>>> wby-app-upms-server 开始启动 <<<<<<");
        SpringApplication.run(UpmsServerApplication.class, args);
        logger.info(">>>>>> wby-app-upms-server 启动完成 <<<<<<");
    }

    /**
     * 打war包
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(UpmsServerApplication.class);
    }
}
