package com.hs.zero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fq
 */
@SpringBootApplication
//        (exclude = {
//        RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class
//})
//@EnableScheduling
@EnableScheduling
//@MapperScan("com.hs.zero.mapper")
public class DemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("zero demo start!");
    }

}
