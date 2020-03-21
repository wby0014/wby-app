package com.example.wby.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration("agentConfigProperties")
@ConfigurationProperties(
        prefix = "service"
)
@PropertySource({"classpath:service.properties"})
public class AgentConfigProperty {
    private String configPropertiesPath;
    private String installPropertiesPath;
    private String componentId;
    private String segmentId;

    public AgentConfigProperty() {
    }

    public String getConfigPropertiesPath() {
        return this.configPropertiesPath;
    }

    public void setConfigPropertiesPath(String configPropertiesPath) {
        this.configPropertiesPath = configPropertiesPath;
    }

    public String getInstallPropertiesPath() {
        return this.installPropertiesPath;
    }

    public void setInstallPropertiesPath(String installPropertiesPath) {
        this.installPropertiesPath = installPropertiesPath;
    }

    public String getComponentId() {
        return this.componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getSegmentId() {
        return this.segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

}
