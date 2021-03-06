package xyz.zhouying.javautils.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lmy on 2017/9/16.
 */
public class IpUtil {

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        LoggerUtil.debug(IpUtil.class,"x-forwarded-for ip: " + ip);

        if (! isInvalidIp(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }

        if (isInvalidIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            LoggerUtil.debug(IpUtil.class, "Proxy-Client-IP ip: " + ip);
        }

        if (isInvalidIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            LoggerUtil.debug(IpUtil.class, "WL-Proxy-Client-IP ip: " + ip);
        }

        if (isInvalidIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            LoggerUtil.debug(IpUtil.class, "HTTP_CLIENT_IP ip: " + ip);
        }

        if (isInvalidIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            LoggerUtil.debug(IpUtil.class, "HTTP_X_FORWARDED_FOR ip: " + ip);
        }

        if (isInvalidIp(ip)) {
            ip = request.getHeader("X-Real-IP");
            LoggerUtil.debug(IpUtil.class, "X-Real-IP ip: " + ip);
        }

        if (isInvalidIp(ip)) {
            ip = request.getRemoteAddr();
            LoggerUtil.debug(IpUtil.class, "getRemoteAddr ip: " + ip);
        }

        LoggerUtil.debug(IpUtil.class, "获取客户端ip: " + ip);
        return ip;
    }

    private static boolean isInvalidIp(String ip) {

        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }
}
