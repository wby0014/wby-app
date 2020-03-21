package com.example.wby.common.interceptor;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wubinyu
 */
public class ErrorInterceptor extends HandlerInterceptorAdapter {

    private String errorPage403;
    private String errorPage404;
    private String errorPage500;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        if (httpServletResponse.getStatus() == 403 && StringUtils.isNotEmpty(this.errorPage403)) {
            modelAndView.setViewName("redirect:" + this.errorPage403);
        } else if (httpServletResponse.getStatus() == 404 && StringUtils.isNotEmpty(this.errorPage404)) {
            modelAndView.setViewName("redirect:" + this.errorPage404);
        } else if (httpServletResponse.getStatus() == 500 && StringUtils.isNotEmpty(this.errorPage500)) {
            modelAndView.setViewName("redirect:" + this.errorPage500);
        }

    }

    public String getErrorPage403() {
        return this.errorPage403;
    }

    public void setErrorPage403(String errorPage403) {
        this.errorPage403 = errorPage403;
    }

    public String getErrorPage404() {
        return this.errorPage404;
    }

    public void setErrorPage404(String errorPage404) {
        this.errorPage404 = errorPage404;
    }

    public String getErrorPage500() {
        return this.errorPage500;
    }

    public void setErrorPage500(String errorPage500) {
        this.errorPage500 = errorPage500;
    }
}
