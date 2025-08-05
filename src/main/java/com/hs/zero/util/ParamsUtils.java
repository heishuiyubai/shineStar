package com.hs.zero.util;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验
 */
public class ParamsUtils {

    public static final String PHONE_BLUR_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    public static final String PHONE_BLUR_REPLACE_REGEX = "$1****$2";


    public static final String blurPhone(String phone) {
        return phone.replaceAll(PHONE_BLUR_REGEX, PHONE_BLUR_REPLACE_REGEX);
    }

    public static void main(String[] args) {
        String name = "s是的是的\uD83D\uDC71ss";
        System.out.println(name);
        System.out.println(replaceEmoji(name," "));

    }

    public static String replaceEmoji(String source, String slipStr) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }

}
