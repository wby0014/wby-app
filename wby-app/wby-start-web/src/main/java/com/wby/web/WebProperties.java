package com.wby.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 制作start的第1步：定义ConfigurationProperties
 * url有个默认值，如果在application.properties文件中配置了wby.url=www.baidu.com则会覆盖默认值
 *
 * @author wubinyu
 * @date 2019/8/22 14:56.
 */
@ConfigurationProperties(prefix = "wby")
@Setter
@Getter
public class WebProperties {

    private String url = "http://www.0014wby.com";
}
