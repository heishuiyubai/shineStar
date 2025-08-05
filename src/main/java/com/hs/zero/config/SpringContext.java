package com.hs.zero.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author fq
 * @date 2021-10-09
 * 通过类名获取bean
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }

    public static <T> T getBean(String name, Class<T> cal) {
        return applicationContext.getBean(name, cal);
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}
