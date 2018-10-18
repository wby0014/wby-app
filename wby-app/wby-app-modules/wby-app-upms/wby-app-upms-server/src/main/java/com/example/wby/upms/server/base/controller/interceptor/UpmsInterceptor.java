package com.example.wby.upms.server.base.controller.interceptor;

import com.example.wby.common.util.PropertiesFileUtil;
import com.example.wby.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录信息拦截器
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsInterceptor.class);
    private static final String ZHENG_OSS_ALIYUN_OSS_POLICY = PropertiesFileUtil.getInstance("zheng-oss-client").get("zheng.oss.aliyun.oss.policy");

    @Autowired
    UpmsApiService upmsApiService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("ZHENG_OSS_ALIYUN_OSS_POLICY", ZHENG_OSS_ALIYUN_OSS_POLICY);
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            return true;
        }
        // 登录信息
//        Subject subject = SecurityUtils.getSubject();
//        String username = (String) subject.getPrincipal(s);
        String s = upmsApiService.selectUpmsApiInfo("测试接口方法信息");
        request.setAttribute("upmsUser", s);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
