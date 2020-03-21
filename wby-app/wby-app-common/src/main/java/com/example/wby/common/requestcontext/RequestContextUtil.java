package com.example.wby.common.requestcontext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class RequestContextUtil {

    private static Logger log = LoggerFactory.getLogger(RequestContextUtil.class);
    private static Locale DEFAULT_LOCALE;
    private static List<String> SUPPORT_LOCALE;
    private static ThreadLocal<HttpServletRequest> request;
    private static ThreadLocal<HttpServletResponse> response;
    private static ThreadLocal<UserSession> userSession;

    public RequestContextUtil() {
    }

    public static void setDefaultLocale(Locale locale) {
        DEFAULT_LOCALE = locale;
    }

    public static void setSupportLocales(List<Locale> supportLocales) {
        if (supportLocales != null) {
            SUPPORT_LOCALE = (List)supportLocales.stream().map((e) -> {
                return e.toString();
            }).collect(Collectors.toList());
        }

    }

    public static void setUserSession(UserSession info) {
        userSession.set(info);
    }

    public static UserSession getUserSession() {
        return (UserSession)userSession.get();
    }

    public static void setRequest(HttpServletRequest request) {
        RequestContextUtil.request.set(request);
    }

    public static void setResponse(HttpServletResponse response) {
        RequestContextUtil.response.set(response);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)request.get();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)response.get();
    }

    private static Locale getDefaultLocale() {
        if (getRequest() != null) {
            Locale locale = getRequest().getLocale();
            if (locale != null && !StringUtils.isEmpty(locale.toString())) {
                return !CollectionUtils.isEmpty(SUPPORT_LOCALE) && SUPPORT_LOCALE.contains(locale.toString()) ? locale : DEFAULT_LOCALE;
            } else {
                return DEFAULT_LOCALE;
            }
        } else {
            return DEFAULT_LOCALE;
        }
    }

    public static Locale getLocale() {
        if (getUserSession() == null) {
            log.trace("- userSession is null,getLocal return default language :SIMPLIFIED_CHINESE");
            return getDefaultLocale();
        } else {
            Locale language = getUserSession().getLanguage();
            if (language == null) {
                log.trace("- language is null, return default language :SIMPLIFIED_CHINESE");
                return getDefaultLocale();
            } else {
                return language;
            }
        }
    }

    public static void clear() {
        request.remove();
        response.remove();
        userSession.remove();
    }

    static {
        DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;
        SUPPORT_LOCALE = new ArrayList(10);
        request = new ThreadLocal();
        response = new ThreadLocal();
        userSession = new ThreadLocal();
    }
}
