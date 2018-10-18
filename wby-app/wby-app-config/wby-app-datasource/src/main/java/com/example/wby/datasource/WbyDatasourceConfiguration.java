//package com.example.wby.datasource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
//import com.example.wby.common.util.AESUtil;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//@Configuration
//@EnableConfigurationProperties({DruidStatProperties.class})
//public class WbyDatasourceConfiguration {
//
//    String regex = "^[A-Fa-f0-9]+$";
//    Pattern pattern = Pattern.compile(regex);
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSource(Environment env) throws Exception {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        if (dataSource.getUsername() == null) {
//            String username = env.getProperty("spring.datasource.username");
//            username = decode(username);
//            dataSource.setUsername(username);
//        }
//
//        if (dataSource.getPassword() == null) {
//            String password = env.getProperty("spring.datasource.password");
//            password = decode(password);
//            dataSource.setPassword(password);
//        }
//
//        if (dataSource.getUrl() == null) {
//            dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        }
//
//        if (dataSource.getDriverClassName() == null) {
//            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        }
//
//        if (!"false".equals(env.getProperty("spring.datasource.druid.StatViewServlet.enabled"))) {
//            try {
//                dataSource.setFilters("stat");
//            } catch (SQLException var4) {
//                var4.printStackTrace();
//            }
//        }
//
//        return dataSource;
//    }
//
//    public String decode(String encodedString) throws Exception {
//        Matcher matcher = pattern.matcher(encodedString);
//        if (!matcher.find()) {
//            throw new Exception("================>账号密码不是加密字符串");
//        }
//        String decodedString = AESUtil.aesDecode(encodedString);
//        return decodedString;
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
