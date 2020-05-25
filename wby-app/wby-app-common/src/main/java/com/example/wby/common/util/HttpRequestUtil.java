package com.example.wby.common.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wubinyu
 */
public class HttpRequestUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);

    public HttpRequestUtil() {
    }

    public static String getHost(final HttpServletRequest request) {
        String host = request.getHeader("Host");
        String xHost = request.getHeader("X-Forwarded-Host");
        if (host == null && xHost == null) {
            String serverNameAndePort = request.getServerName() + ":" + request.getServerPort();
            return serverNameAndePort;
        } else if (xHost != null) {
            return !"localhost".equals(host) && !"127.0.0.1".equals(host) ? host : xHost;
        } else {
            return host;
        }
    }

    public static String getRequestProtocolAddrPort(final HttpServletRequest request) {
        String protocol = request.getHeader("X-Forwarded-Proto");
        if (Strings.isNullOrEmpty(protocol)) {
            protocol = "http";
        }

        int port = request.getServerPort();
        String host = request.getHeader("Host");
        if ("https".equals(protocol) && !StringUtils.isEmpty(host) && !host.contains(":")) {
            port = 443;
        }

        return protocol + "://" + request.getServerName() + ":" + port;
    }

    public static String getHostFromURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return url.getHost();
        } catch (MalformedURLException var2) {
            return "";
        }
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            return index != -1 ? ip.substring(0, index) : ip;
        } else {
            ip = request.getHeader("X-Real-IP");
            if (ip != null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                return ip;
            } else {
                ip = request.getHeader("Proxy-Client-IP");
                if (ip != null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                    return ip;
                } else {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                    if (ip != null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                        return ip;
                    } else {
                        ip = request.getRemoteAddr();
                        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
                    }
                }
            }
        }
    }
}
