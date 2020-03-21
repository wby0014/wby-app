package com.example.wby.common.config;

import com.example.wby.common.properties.IAgentService;
import com.example.wby.common.properties.ServiceProperties;
import com.example.wby.common.requestcontext.RequestContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 工程初始化配置
 */
@Component
public class InitConfig {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private ServiceProperties serviceProperties;


    @PostConstruct
    public void init() {
        try {
            //设置默认多语言
            String defaultLanguage = serviceProperties.getDefaultLanguage();
            String[] languages = defaultLanguage.split("_");
            if (languages.length > 1) {
                RequestContextUtil.setDefaultLocale(new Locale(languages[0], languages[1]));
            }

            //初始化追踪日志
            //            TraceRecordGenerator.traceInit(agentService.getComponentId(), agentService.getSegmentId(), agentService.getLocalIndex() == null ? "" : agentService.getLocalIndex().toString());

            new Thread(() -> {
                //如果启动csrfHost安全拦截，则获取合法的ip
                //                legalIpService.refreshLegalIpSet();
            }).start();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
