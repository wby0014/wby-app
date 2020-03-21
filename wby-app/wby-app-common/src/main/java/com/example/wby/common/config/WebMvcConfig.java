package com.example.wby.common.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.wby.common.interceptor.CustomJackson2HttpMessageConverter;
import com.example.wby.common.interceptor.ErrorInterceptor;
import com.example.wby.common.interceptor.FrontServiceInterceptor;
import com.example.wby.common.interceptor.RestServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * WebMvc配置：
 *  1.拦截器配置，默认提供了api接口的认证拦截，具体url规则可调整
 *  2.消息转换器的配置，提供Jackson的支持
 * @author wubinyu
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    StringHttpMessageConverter stringConvert;

    @Value("${seashell.server.error_page.403:}")
    private String errorPage403;

    @Value("${seashell.server.error_page.404:}")
    private String errorPage404;

    @Value("${seashell.server.error_page.500:}")
    private String errorPage500;

    @Value("${sys.token.access}")
    private Boolean sysTokenAccess;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //前端请求的追踪日志，如需要则打开注释
        registry.addInterceptor(new FrontServiceInterceptor()).addPathPatterns("/front/**");
        if(sysTokenAccess){
            //对外提供的api接口验证及追踪日志
            registry.addInterceptor(new RestServiceInterceptor()).addPathPatterns("/api/**");
        }
        //错误页面的跳转
        if (StringUtils.isNotEmpty(errorPage403) || StringUtils.isNotEmpty(errorPage404) || StringUtils.isNotEmpty(errorPage500)) {
            ErrorInterceptor error = new ErrorInterceptor();
            error.setErrorPage403(errorPage403);
            error.setErrorPage404(errorPage404);
            error.setErrorPage500(errorPage500);
            registry.addInterceptor(error).addPathPatterns("/**");
        }

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(new CustomJackson2HttpMessageConverter().builder()));
        converters.add(stringConvert);
    }


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();

        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            add(new MediaType("text", "plain", Charset.forName("UTF-8")));
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        return stringConvert;
    }

}
