package com.hs.zero.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fq
 */
@Component
public class ThreadPoolManager {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    //线程池要素
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAXI_MUN_POOL_SIZE = 8;
    private static final long KEEP_ALIVE_TIME= 30000L;
    private static final TimeUnit unit = TimeUnit.SECONDS;
    private static final LinkedBlockingQueue WORK_QUEUE = new LinkedBlockingQueue<>(2048);
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXI_MUN_POOL_SIZE, KEEP_ALIVE_TIME, unit, WORK_QUEUE);


    public void putTaskToQueue(Runnable task){
        // 线程池日志打印
        String log = "ThreadPoolManager "
                + "Task[" + task.getClass().getSimpleName() + "] "
                + "CorePoolSize[" + threadPoolExecutor.getCorePoolSize() + "] "
                + "MaximumPoolSize[" + threadPoolExecutor.getMaximumPoolSize() + "] "
                + "KeepAliveTimeSecond[" + threadPoolExecutor.getKeepAliveTime(TimeUnit.SECONDS) + "] "
                + "ActiveCount[" + threadPoolExecutor.getActiveCount() + "] "
                + "PoolSize[" + threadPoolExecutor.getPoolSize() + "] "
                + "TaskCount[" + threadPoolExecutor.getTaskCount() + "] "
                + "CompletedTaskCount[" + threadPoolExecutor.getCompletedTaskCount() + "] "
                ;
        logger.info(log);
        threadPoolExecutor.execute(task);
    }


}
