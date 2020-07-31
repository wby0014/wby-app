package com.example.wby.plugin.frame.annotation;

import com.example.wby.plugin.frame.enums.ProcessTypeEnum;

import java.lang.annotation.*;

/**
 * @Description
 * @Date 2020/7/31 17:29
 * @Author wuby31052
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Process {

    ProcessTypeEnum type();

    // 同阶段中执行顺序由小到大，负数值被系统保留使用，应用可以以0开始
    int order();

}
