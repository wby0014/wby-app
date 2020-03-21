package com.example.wby.common.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class ConfigLoadUtils {

    private static Logger logger = LoggerFactory.getLogger(ConfigLoadUtils.class);

    public ConfigLoadUtils() {
    }

    public static Resource[] getResources(String pattern) {
        PathMatchingResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        String serviceModelPattern = "classpath*:" + pattern;

        try {
            return loader.getResources(serviceModelPattern);
        } catch (IOException var4) {
            logger.error("Config path load error, load config error on path ", new String[]{"pattern"}, pattern, var4);
            return null;
        }
    }

    public static Resource getResource(String pattern) {
        Resource[] resArray = getResources(pattern);
        return resArray != null && resArray.length > 0 ? resArray[0] : null;
    }
}
