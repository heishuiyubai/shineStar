package com.hs.zero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hs
 * 正则表达式
 */
public class RegularUtils {
    public static void main(String[] args) {


    }

    /**
     * 加密
     * @author Administrator
     */
    public static class EncryptionUtil {

        final static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);


        public static void main(String[] args) {

            String result = strFormat("中国", "张三");
            System.out.println(result);
        }

        /**
         * 加密
         */
        public void encryptor() {

        }

        /**
         * 字符串格式化
         */
        public static String strFormat(String country, String name) {
            return String.format("this is country  %s , name is %s", country, name);
        }
    }
}
