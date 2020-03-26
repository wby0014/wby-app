package com.example.wby.common.ex;

import com.example.wby.common.requestcontext.RequestContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;


public class I18nUtils {

    private static final Logger logger = LoggerFactory.getLogger(I18nUtils.class);

    public I18nUtils() {
    }

    public static String getMessage(String key) {
        return getMessage(key, new Object[0]);
    }

    public static String getMessage(String key, String defaultMsg) {
        return getMessage(key, defaultMsg, new Object[0]);
    }

    public static String getMessage(String key, Object[] params) {
        Locale locale = RequestContextUtil.getLocale();
        if (params == null) {
            params = new Object[0];
        }

        return getMessage(key, params, locale);
    }

    public static String getMessage(String key, String defaultMsg, Object[] params) {
        Locale locale = RequestContextUtil.getLocale();
        return getMessage(key, params, defaultMsg, locale);
    }

    public static String getMessage(String key, Locale locale) {
        return getMessage(key, new Object[0], locale);
    }

    public static String getMessage(String key, Object[] params, Locale locale) {
        try {
            return AppContext.getContext().getMessage(key, params, locale);
        } catch (Exception var4) {
            logger.error("get locale msg error", var4);
            return key;
        }
    }

    public static String getMessage(String key, Object[] params, String defaultMsg, Locale locale) {
        return AppContext.getContext().getMessage(key, params, defaultMsg, locale);
    }

}
