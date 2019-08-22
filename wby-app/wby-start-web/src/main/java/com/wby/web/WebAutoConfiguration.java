package com.wby.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 制作start的第2步：创建AutoConfiguration
 *
 * @author wubinyu
 * @date 2019/8/22 15:00.
 */
@Configuration
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration {

    @Resource
    private WebProperties webProperties;

    @Bean
    @ConditionalOnMissingBean
    public WebClient init() {
        WebClient webClient = new WebClient();
        String url = webProperties.getUrl();
        webClient.setUrl(url);
        return webClient;
    }

}
