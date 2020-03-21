package com.example.wby.common.filter;

import com.example.wby.common.requestcontext.RequestContextUtil;
import com.example.wby.common.requestcontext.UserSession;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;


/**
 * 将相关信息保存在RequestContextUtil里面
 * 1.保存当前request和response，以便在Controller和service层代码直接拿到请求和返回信息
 * 使用方式如：HttpServletRequest request = RequestContextUtil.getRequest()
 * 2.保存登录用户的session信息及多语言标识的处理
 * 使用方式如：UserSession userSession = RequestContextUtil.getUserSession()
 * Local userLocal = RequestContextUtil.getLocale()
 */
public class RequestContextFilter implements Filter {

    /**
     * 默认管理员用户，开发的时候可以打开
     */
    private boolean sysUser = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void setDevParams(boolean sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            RequestContextUtil.setRequest(httpRequest);
            RequestContextUtil.setResponse((HttpServletResponse) response);

            HttpSession session = httpRequest.getSession();
            if (sysUser) {
                UserSession userSession = new UserSession();
                userSession.setUserId("admin");
                userSession.setLanguage(new Locale("zh", "CN"));
                RequestContextUtil.setUserSession(userSession);
            } else {
                if (session != null) {
                    //                    Assertion assertion = (Assertion) httpRequest.getSession().getAttribute("_const_cas_assertion_");
                    //                    if (assertion != null && assertion.getPrincipal() != null) {
                    //                        String name = assertion.getPrincipal().getName();
                    String name = "admin&&xxxx&&22&&33&&33&&33&&dd";
                    String[] infos = name.split("&&");
                    if (infos.length >= 6) {
                        UserSession userSession = new UserSession();
                        userSession.setUserId(infos[0]);
                        userSession.setPersonId(infos[1]);
                        userSession.setClientIp(infos[2]);
                        userSession.setClientMac(infos[3]);
                        userSession.setTgc(infos[4]);
                        //处理多语言标识，多语言可能是中划线或下划线
                        String language = infos[5];
                        String[] arrays = language.split("-");
                        if (arrays.length == 1) {
                            String[] tempArray = language.split("_");
                            if (tempArray.length > 1) {
                                arrays = tempArray;
                            }
                        }
                        if (arrays.length == 2) {
                            userSession.setLanguage(new Locale(arrays[0], arrays[1]));
                        } else if (arrays.length == 1) {
                            userSession.setLanguage(new Locale(arrays[0], ""));
                        }
                        RequestContextUtil.setUserSession(userSession);
                    }
                    //                    }
                }
            }
            chain.doFilter(request, response);
        } finally {
            RequestContextUtil.clear();
        }
    }

    @Override
    public void destroy() {

    }

}
