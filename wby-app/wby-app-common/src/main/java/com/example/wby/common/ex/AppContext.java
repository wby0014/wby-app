package com.example.wby.common.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class AppContext implements ApplicationContextAware {
    private static ApplicationContext context = null;
    private static final Logger logger = LoggerFactory.getLogger(AppContext.class);

    public AppContext() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    public static void setContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name) {
        if (context == null) {
            throw new IllegalStateException("applicaitonContext un inject");
        } else {
            try {
                return (T) context.getBean(name);
            } catch (BeansException var2) {
                logger.error("[0x00010001] - get Bean error", var2);
                return null;
            }
        }
    }

    public static <T> T getBeanByClass(Class className) {
        if (context == null) {
            throw new IllegalStateException("applicaitonContext un inject");
        } else {
            try {
                return (T) context.getBean(className);
            } catch (BeansException var2) {
                logger.error("[0x00010001] - get Bean by className error", var2);
                return null;
            }
        }
    }
}
