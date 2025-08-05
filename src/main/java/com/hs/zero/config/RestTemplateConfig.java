package com.hs.zero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * 配置restTemplate用于http接口调用
 *
 * @author fq
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 配置链接工厂
     * 配置超时时间：读和链接超时
     *
     * @return
     */
    @Bean
    public ClientHttpRequestFactory simpClientHttpRequestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }

    /**
     * rest模板
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        // boot中可使用RestTemplateBuilder.build创建
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = builder.build();
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        return restTemplate;
//    }


}
