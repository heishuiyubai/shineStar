package com.hs.zero.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class TraceIdUtil {

    public final static String TRACE_ID = "traceId";
    public final static String PREFIX = "zero-";

    public static String getTradeId(){
        int date = LocalDateTime.now().getDayOfYear();
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        return PREFIX.concat(String.valueOf(date)).concat(substring);
    }

    public static void main(String[] args) {
        System.out.printf(getTradeId());
    }
}
