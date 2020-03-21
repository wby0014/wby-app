package com.example.wby.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * seashell.properties的配置文件
 * 后续可添加更多的配置项
 *
 */
@PropertySource("classpath:service.properties")
@Configuration
public class ServiceProperties {


    @Value("${service.db.segmentId}")
    private String dbSegmentId;

    @Value("${service.errorcode.profix}")
    private String errorCodePrefix;

    @Value("${service.language.default}")
    private String defaultLanguage;

    @Value("${service.page.urls}")
    private String pageUrls;

    @Value("${service.page.dispatchUrl}")
    private String pageDispatchUrl;

    @Value("${service.mq.segmentId}")
    private String mqSegmentId;

    @Value("${service.cache.segmentId}")
    private String cacheSegmentId;


    @Value("${service.segmentId}")
    private String webSegmentId;

    @Value("${cpams.third.queue}")
    private String resourceNotifyThird;

    public String getResourceNotifyThird() {
        return resourceNotifyThird;
    }

    public ServiceProperties setResourceNotifyThird(String resourceNotifyThird) {
        this.resourceNotifyThird = resourceNotifyThird;
        return this;
    }

    public String getWebSegmentId() {
        return webSegmentId;
    }

    public ServiceProperties setWebSegmentId(String webSegmentId) {
        this.webSegmentId = webSegmentId;
        return this;
    }

    public String getDbSegmentId() {
        return dbSegmentId;
    }

    public void setDbSegmentId(String dbSegmentId) {
        this.dbSegmentId = dbSegmentId;
    }


    public String getErrorCodePrefix() {
        return errorCodePrefix;
    }

    public void setErrorCodePrefix(String errorCodePrefix) {
        this.errorCodePrefix = errorCodePrefix;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getPageUrls() {
        return pageUrls;
    }

    public void setPageUrls(String pageUrls) {
        this.pageUrls = pageUrls;
    }

    public String getPageDispatchUrl() {
        return pageDispatchUrl;
    }

    public void setPageDispatchUrl(String pageDispatchUrl) {
        this.pageDispatchUrl = pageDispatchUrl;
    }

    public String getMqSegmentId() {
        return mqSegmentId;
    }

    public void setMqSegmentId(String mqSegmentId) {
        this.mqSegmentId = mqSegmentId;
    }

    public String getCacheSegmentId() {
        return cacheSegmentId;
    }

    public void setCacheSegmentId(String cacheSegmentId) {
        this.cacheSegmentId = cacheSegmentId;
    }
}
