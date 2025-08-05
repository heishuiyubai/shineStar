package com.hs.zero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池配置
 */
@Configuration
@EnableAsync
public class AsyncTaskConfig {

    @Bean(value = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int core = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(core);
        executor.setMaxPoolSize(core * 2 + 1);
        executor.setKeepAliveSeconds(5);
        executor.setQueueCapacity(9999);
        executor.setThreadNamePrefix("async-task-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(false);
        executor.initialize();
        return executor;
    }

}
