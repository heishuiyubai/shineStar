package com.hs.zero.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * @author hs
 * 增加代码本身的阅读性，来减少相应的注解
 */
public class DateUtils {

    private static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    public static String TIME_WITHOUT_SEC_PATTERN = "HH:mm";

    public static String DAY_BEGIN = "00:00:00";
    public static String DAY_END = "23:59:59";
    /**
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String formatDate = format1.format(date);
        return formatDate;
    }

    /**
     * 根据num获取相应日期的的sunday
     *
     * @param num
     * @return
     */
    public static final int getSunday(int num) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -num);
        calendar.add(Calendar.WEEK_OF_MONTH, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println(sf.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        String format = sf.format(calendar.getTime());
        return Integer.valueOf(format);
    }

    public static String getAppointTime(int year) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = now.plusYears(10);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }

    /**
     * 时间格式化
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_STR);
        String formatDate = dateTime.format(formatter);
        return formatDate;
    }

    /**
     * 时间格式化
     * @param dateTime
     * @param formatStr
     * @return
     */
    public static String format(LocalDateTime dateTime, String formatStr) {
        if (StringUtils.isBlank(formatStr)) {
            return format(dateTime);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
        return dateTime.format(formatter);
    }

    public static LocalTime parseTime(String time, String pattern) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            return null;
        }
    }


    private static boolean checkPayTime(LocalDateTime payTime) {
        boolean flag = false;
        if(payTime!=null){
            LocalDateTime endTime = LocalDateTime.of(2024, 4, 16, 15, 10);
            Duration between = Duration.between(payTime, endTime);
            long millis = between.toMinutes();
            System.out.println(millis);
            if(millis < 5){
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        checkDifferent();


    }
    public static Date convertToDate(LocalDateTime date) {
        if(null ==date){
            return new Date();
        }
        ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static void checkDifferent(){
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis() + 10000); // 当前时间加上10秒
        long second = (date2.getTime() - date1.getTime()) / 1000;
        System.out.println(second);
    }




}
