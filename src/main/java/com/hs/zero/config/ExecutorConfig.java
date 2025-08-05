package com.hs.zero.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fq
 * 线程池的配置：异步线程池配置
 * 这里写死了一些参数，线程池的参数应该放在配置文件里，根据服务器性能去配置，而不是事前写定
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    private static final Logger log = LoggerFactory.getLogger(ExecutorConfig.class);

    private int corePoolSize = 5;

    private int maxPoolSize = 10;

    private  int queueCapacity = 99;

    private  String namePrefix = "zero_";

    @Bean(name = "asyncServiceExecutor")
    public Executor asyServiceExecutor(){
        log.info("start asyServiceExecutor!");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);
        //配置线程池中的线程前缀
        executor.setThreadNamePrefix(namePrefix);
        //拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        log.info("init asyServiceExecutor end!");
        return executor;
    }

}
