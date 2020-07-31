package com.example.wby.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 启动类
 * @Date 2020/7/3 16:34
 * @Author wuby31052
 */
@SpringBootApplication
@MapperScan("com.example.wby.plus.*.mapper")
public class BPDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BPDemoApplication.class, args);
    }
}
