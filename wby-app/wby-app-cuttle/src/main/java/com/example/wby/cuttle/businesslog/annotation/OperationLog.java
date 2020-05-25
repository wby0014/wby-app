package com.example.wby.cuttle.businesslog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wubinyu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    String moduleId() default "";

    String action();

    String actionDetail() default "";

    int actionMultiLang() default 0;

    String actionMessageId() default "";

    String terminalType() default "0";

    String objectType() default "";

}
