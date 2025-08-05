package com.hs.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fq
 */
public class LoggerUtils {
    /**
     * http请求的logger
     */
    public static Logger HTTP_ACCESS = LoggerFactory.getLogger("HTTP_ACCESS_LOGGER");
    /**
     * 定时任务
     */
    public static Logger JOB = LoggerFactory.getLogger("JOB_LOGGER");
    /**
     * 配置文件
     */
    public static Logger CONFIG_JOB = LoggerFactory.getLogger("CONFIG_LOG");


}
