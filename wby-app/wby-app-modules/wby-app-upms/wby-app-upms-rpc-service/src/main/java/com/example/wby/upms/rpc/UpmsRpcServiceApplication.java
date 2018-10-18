package com.example.wby.upms.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * 服务启动类
 *
 * @author wubinyu
 * @date 2018/10/15 12:02.
 */
@MapperScan(basePackages= {"com.example.wby.**.mapper"})
@ComponentScan(basePackages= {"com.example"})
@ImportResource({"classpath:dubbo-provider.xml"})
@SpringBootApplication
public class UpmsRpcServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(UpmsRpcServiceApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>>>> wby-app-upms-rpc-service 正在启动 <<<<<<<");
        SpringApplication.run(UpmsRpcServiceApplication.class, args);
        logger.info(">>>>>>> wby-app-upms-rpc-service 启动完成 <<<<<<<");
    }
}
