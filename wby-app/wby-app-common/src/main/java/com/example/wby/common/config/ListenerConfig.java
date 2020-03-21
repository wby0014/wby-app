package com.example.wby.common.config;

import com.example.wby.common.listener.JdbcDriverRegistrationListener;
import com.example.wby.common.listener.ThreadDestroyListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器配置
 **/
@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean jdbcDriverRegistrationListener() {
        ServletListenerRegistrationBean<JdbcDriverRegistrationListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new JdbcDriverRegistrationListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean threadDestroyListener() {
        ServletListenerRegistrationBean<ThreadDestroyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new ThreadDestroyListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    @ConditionalOnExpression("${seashell.cas.enabled:true}==true")
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        /*ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;*/
        return null;
    }

}
