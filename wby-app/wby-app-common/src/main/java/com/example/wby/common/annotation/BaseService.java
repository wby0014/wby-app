package com.example.wby.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * @author wubinyu
 * @date 2018/10/15 12:08.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
