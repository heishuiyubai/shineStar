package com.hs.zero.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class IpUtils {

    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip == null) {
            ip = "";
        }

        List<String> ipList = split2list(ip, "\\,");
        return ipList != null && !ipList.isEmpty() ? (String)ipList.get(0) : ip;
    }

    public static List<String> split2list(String str, String regex) {
        List<String> retList = new ArrayList();
        if (str == null) {
            return retList;
        } else {
            String[] words = str.split(regex);

            for(int i = 0; i < words.length; ++i) {
                if (words[i] != null && !words[i].trim().equals("")) {
                    retList.add(words[i]);
                }
            }

            return retList;
        }
    }
}
