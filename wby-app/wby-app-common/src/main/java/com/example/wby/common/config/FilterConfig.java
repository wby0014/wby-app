package com.example.wby.common.config;

import com.example.wby.common.filter.CsrfHostFilter;
import com.example.wby.common.filter.FrontRouterFilter;
import com.example.wby.common.filter.RequestContextFilter;
import com.example.wby.common.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 过滤器配置
 * 1.单点登录的配置，order顺序请勿修改
 * 2.RequestContextFilter请求上下文的自定义拦截器
 * 3.解决前端history路由模式的页面模块切换
 */
@Configuration
public class FilterConfig {

    @Autowired
    private ServiceProperties serviceProperties;

    @Value("${seashell.server.error_page.403:}")
    private String errorPage403;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${sys.user.open}")
    private boolean sysUser;

    @Bean
    @ConditionalOnExpression("${seashell.csrfhost.enable:true}==true")
    public FilterRegistrationBean csrfHostFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CsrfHostFilter filter = new CsrfHostFilter();
        registrationBean.setFilter(filter);
        registrationBean.addInitParameter(CsrfHostFilter.IGNORE_PAGES, contextPath + errorPage403);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("csrfHostFilter");
        registrationBean.setOrder(-1);
        return registrationBean;
    }

    @Bean
    @ConditionalOnExpression("${seashell.cas.enabled:true}==true")
    public FilterRegistrationBean casLogoutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //        registrationBean.setFilter(new SingleSignOutFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CASLogoutFilter");
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    @ConditionalOnExpression("${seashell.cas.enabled:true}==true")
    public FilterRegistrationBean casFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //        registrationBean.setFilter(new HikAuthenticationFilter());

        //配置不需要单点登录的uri,建议在cas-client.properties中配置
        //registrationBean.addInitParameter("ignorePattern", "/error.jsp,/hello.jsp");
        //registrationBean.addInitParameter("ignoreUrlPatternType","REGEX");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CASFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    @ConditionalOnExpression("${seashell.cas.enabled:true}==true")
    public FilterRegistrationBean casValidationFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //        registrationBean.setFilter(new HikCas20ProxyReceivingTicketValidationFilter());
        registrationBean.addInitParameter("encoding", "UTF-8");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CASValidationFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean commonFilterBean() {
        FilterRegistrationBean registerBean = new FilterRegistrationBean();

        RequestContextFilter contextFilter = new RequestContextFilter();
        contextFilter.setDevParams(sysUser);
        registerBean.setFilter(contextFilter);
        registerBean.addUrlPatterns("/*");
        registerBean.setName("contextFilter");
        registerBean.setOrder(3);
        return registerBean;
    }

    @Bean
    public FilterRegistrationBean frontFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        FrontRouterFilter filter = new FrontRouterFilter();
        registrationBean.addInitParameter(FrontRouterFilter.URL_PATTERNS, serviceProperties.getPageUrls());
        registrationBean.addInitParameter(FrontRouterFilter.DISPATCH_URL, serviceProperties.getPageDispatchUrl());
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("frontFilter");
        registrationBean.setOrder(4);
        return registrationBean;
    }
}
