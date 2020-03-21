package com.example.wby.common.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 前端路由（FrontRouterFilter）：由于前端vue更多的采用history模式的路由处理，该拦截器用于将不同模块路径统一返回到指定的页面中
 */
public class FrontRouterFilter implements Filter {

    public static final String DISPATCH_URL = "dispatchUrl";
    public static final String URL_PATTERNS = "urlPatterns";
    private List<String> frontRoute = new ArrayList();
    private String dispatchUrl;
    private PathMatcher pathMatcher = new AntPathMatcher();

    public FrontRouterFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("urlPatterns");
        if (!StringUtils.isEmpty(urls)) {
            String[] urlsArray = urls.split(",");
            this.frontRoute.addAll(Arrays.asList(urlsArray));
        }

        this.dispatchUrl = filterConfig.getInitParameter("dispatchUrl");
    }

    private boolean match(String requestUri) {
        Iterator var2 = this.frontRoute.iterator();

        String router;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            router = (String)var2.next();
        } while(!this.pathMatcher.match(router, requestUri));

        return true;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        String requestURI = request.getRequestURI();
        if (this.match(requestURI)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(this.dispatchUrl);
            if (null != requestDispatcher) {
                requestDispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
