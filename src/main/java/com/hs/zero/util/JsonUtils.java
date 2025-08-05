package com.hs.zero.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gmmsj.common.component.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON工具类，使用jackson库
 * 注意：不应在ApplicationContext启动完成之前使用。如有ApplicationContext启动完成之前使用，直接new ObjectMapper使用即可。
 */

public class JsonUtils {
    public static ObjectMapper getMapper() {
        // 使用Spring注入的ObjectMapper 与Spring的序列化行为保持一致
//        return SpringContextHolder.getBean(ObjectMapper.class);
        return new ObjectMapper();
    }

    public static <T> T fromJson(String jsonString, Class<T> type) {
        try {
            return getMapper().readValue(jsonString, type);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T fromJson(String jsonString, TypeReference<T> valueTypeRef) {
        try {
            return getMapper().readValue(jsonString, valueTypeRef);
        } catch (IOException e) {
            return null;
        }
    }

    public static String toJson(Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }

    public static String toPrettyJson(Object obj) {
        try {
            return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }



    /**
     * json 字符串转数组
     * @param str
     * @return
     */
    public static <T> List<T> toList(String str, Class<T> to) {
        JavaType javaType = getCollectionType(ArrayList.class, to);
        List<T> list = null;
        try {
            list = (List<T>) getNewMapper().readValue(str, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 当获取mapper为null时，获取新的对象
     * @return
     */
    public static ObjectMapper getNewMapper() {
        ObjectMapper mapper = getMapper();
        if (null == mapper) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getNewMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


}
