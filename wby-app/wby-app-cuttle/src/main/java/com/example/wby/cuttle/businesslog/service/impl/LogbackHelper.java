package com.example.wby.cuttle.businesslog.service.impl;

import com.example.wby.cuttle.businesslog.service.IRunningLogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author wubinyu
 */
@Service
@ConditionalOnMissingBean({IRunningLogHelper.class})
public class LogbackHelper implements IRunningLogHelper {

    private static final Logger logger = LoggerFactory.getLogger(LogbackHelper.class);
    public static final String DEFAULT = "default";

    public LogbackHelper() {
    }

    public String getLogLevel(String logName) {
        return this.getLogLevel("default", logName);
    }

    public String getLogLevel(String context, String logName) {
        try {
            ObjectName name = new ObjectName("ch.qos.logback.classic:Name=" + context + ",Type=ch.qos.logback.classic.jmx.JMXConfigurator");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            Object result = server.invoke(name, "getLoggerLevel", new String[]{logName}, new String[]{"java.lang.String"});
            return result != null && result instanceof String ? (String) result : null;
        } catch (Exception var6) {
            logger.error("get log level error", new String[]{"context", "logName"});
            return null;
        }
    }

    public void setLogLevel(String context, String logName, String logLevel) {
        try {
            String oldLogLevel = this.getLogLevel(context, logName);
            ObjectName name = new ObjectName("ch.qos.logback.classic:Name=" + context + ",Type=ch.qos.logback.classic.jmx.JMXConfigurator");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.invoke(name, "setLoggerLevel", new String[]{logName, logLevel}, new String[]{"java.lang.String", "java.lang.String"});
            logger.warn("set Logger Level success", new String[]{"context", "logName", "oldLogLevel", "newLogLevel"});
        } catch (Exception var7) {
            logger.error("set log level error", new String[]{"logName", "logLevel"});
        }

    }

    public void setLogLevel(String logName, String logLevel) {
        this.setLogLevel("default", logName, logLevel);
    }
}
