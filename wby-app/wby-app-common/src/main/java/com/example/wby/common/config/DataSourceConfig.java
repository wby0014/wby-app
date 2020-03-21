package com.example.wby.common.config;

import com.example.wby.common.properties.ServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源相关配置
 */
@Configuration
public class DataSourceConfig {


    @Autowired
    private ServiceProperties serviceProperties;

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() throws Exception {
        return _initDataSource();
    }

    private DataSource _initDataSource() throws SQLException {
        /*String dbSegmentId = serviceProperties.getDbSegmentId();

        //后面需要解密
        String password = agentService.getConfigPropertyValueAutoDecrypt(dbSegmentId, ConfigureConstants.CONFIG_DB_PASSWORD_SUFFIX);
        String dbIp = agentService.getConfigPropertyValue(dbSegmentId, ConfigureConstants.CONFIG_PARENT_IP_SUFFIX);
        String dbName = agentService.getConfigPropertyValue(dbSegmentId, ConfigureConstants.CONFIG_DB_NAME_SUFFIX);
        String port = agentService.getConfigPropertyValue(dbSegmentId, ConfigureConstants.CONFIG_PARENT_PORT_SUFFIX);
        String username = agentService.getConfigPropertyValue(dbSegmentId, ConfigureConstants.CONFIG_DB_USERNAME_SUFFIX);

        String datasourcePrefix = "spring.datasource.";

        String url = this.environment.getProperty(datasourcePrefix + "url");

        if (StringUtils.isNotEmpty(url)) {
            url = String.format(url, dbIp, port, dbName);
        }

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setDriverClassName(this.environment.getProperty(datasourcePrefix + "driver-class-name"));
        datasource.setUsername(username);
        datasource.setPassword(password);

        datasource.setInitialSize(ObjectUtils.getIntValue(this.environment.getProperty(datasourcePrefix + "initialSize", Integer.class)));
        datasource.setMinIdle(ObjectUtils.getIntValue(this.environment.getProperty(datasourcePrefix + "minIdle", Integer.class)));
        datasource.setMaxWait(ObjectUtils.getLongValue(this.environment.getProperty(datasourcePrefix + "maxWait", Long.class)));
        datasource.setMaxActive(ObjectUtils.getIntValue(this.environment.getProperty(datasourcePrefix + "maxActive", Integer.class)));
        datasource.setTimeBetweenEvictionRunsMillis(ObjectUtils.getLongValue(this.environment.getProperty(datasourcePrefix
                + "timeBetweenEvictionRunsMillis", Long.class)));
        datasource.setMinEvictableIdleTimeMillis(ObjectUtils.getLongValue(this.environment.getProperty(datasourcePrefix
                + "minEvictableIdleTimeMillis", Long.class, 1800000L)));

        datasource.setValidationQuery(this.environment.getProperty(datasourcePrefix + "validationQuery"));
        datasource.setTestOnBorrow(this.environment.getProperty(datasourcePrefix + "testOnBorrow", Boolean.class));
        datasource.setTestOnReturn(this.environment.getProperty(datasourcePrefix + "testOnReturn", Boolean.class));
        datasource.setTestWhileIdle(this.environment.getProperty(datasourcePrefix + "testWhileIdle", Boolean.class));
        datasource.addFilters(this.environment.getProperty(datasourcePrefix + "filters"));
        datasource.setConnectProperties(this.environment.getProperty(datasourcePrefix + "connectionProperties", Properties.class));

        log.info(LogFormatter.toMsg("start init db", "url", "dbType"), datasource.getUrl(), datasource.getDbType());

        while (!initDataSource(datasource)) {
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
                log.error("initDataSource Connect thread is Interrupted {}", e);
            }
        }
        log.info(LogFormatter.toMsg("end init db", "url", "dbType"), datasource.getUrl(), datasource.getDbType());*/
        //        return datasource;
        return null;
    }

    private boolean initDataSource() {
        //      params  DruidDataSource dataSource
       /* try {
            dataSource.init();
        } catch (Exception e) {
            log.error(LogFormatter.toLog(DefaultErrorCode.DB_START_ERROR, "url", "dbType"), dataSource.getUrl(), dataSource.getDbType(), e);
            try {
                dataSource.restart();
            } catch (SQLException e1) {
                log.error("DataSource invalid! restart", e1);
            }
            return false;
        }*/
        return true;
    }


}
