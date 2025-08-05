package com.hs.zero.util;


/**
 * 参数校验工具
 *
 * @author fq
 * @date 2025-02-26
 */
public class ParamUtils {

    /**
     * 校验字符串参数
     *
     * @return boolean
     */
    public static boolean checkStringParam(String param) {
        return param != null && !param.isEmpty() && !param.equals("null");
    }


    /**
     * 校验对象是否有效
     *
     * @param param 待校验的对象
     * @return 如果对象有效则返回 true，否则返回 false
     */
    public static boolean isValidObject(Object param) {
        try {
            if (param == null) {
                return false;
            }
            if (param instanceof String) {
                return isValidString((String) param);
            }
            if (param instanceof Integer) {
                return isValidInteger((Integer) param);
            }
            // 处理其他类型的对象，可以根据需要扩展
            return true; // 默认情况下认为其他类型的对象是有效的
        } catch (Exception e) {
            // 记录日志或采取其他措施
            System.err.println("Error in validating object: " + e.getMessage());
            return false;
        }
    }

    /**
     * 校验字符串是否有效
     *
     * @param str 待校验的字符串
     * @return 如果字符串有效则返回 true，否则返回 false
     */
    private static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * 校验整数是否有效
     *
     * @param num 待校验的整数
     * @return 如果整数有效则返回 true，否则返回 false
     */
    private static boolean isValidInteger(Integer num) {
        return num != null;
    }



}
