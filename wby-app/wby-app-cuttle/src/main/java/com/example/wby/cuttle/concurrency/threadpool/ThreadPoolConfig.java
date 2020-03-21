package com.example.wby.cuttle.concurrency.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 提供了对于默认线程池的配置【该线程池被所有的异步任务共享，而不属于某一个异步任务】，
 * 返回名称为taskExecutor的bean，其中线程池的核心参数有默认配置且可配置
 * @Async("name")可定义所使用线程池的名称，此处如果不写自定义线程池的名称，会使用默认的线程池
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);
    @Value("${thread.pool.executor.core_pool_size:10}")
    private int corePoolSize;
    @Value("${thread.pool.executor.max_pool_size:20}")
    private int maxPoolSize;
    @Value("${thread.pool.executor.queue_capacity:50}")
    private int queueCapacity;
    @Value("${thread.pool.executor.name.prefix:taskExecutor-}")
    private String namePrefix;

    @Override
    @Bean(value = {"taskExecutor"}, destroyMethod = "destroy")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(this.corePoolSize);
        pool.setKeepAliveSeconds(60);
        pool.setMaxPoolSize(this.maxPoolSize);
        pool.setQueueCapacity(this.queueCapacity);
        pool.setThreadNamePrefix(this.namePrefix);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setAwaitTerminationSeconds(60);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.setTaskDecorator((runnable) -> () -> {
            try {
                runnable.run();
            } catch (Exception var2) {
                logger.error("", var2);
            }

        });
        pool.initialize();
        return pool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            logger.error("taskExecutor execute error", throwable);
            logger.error("method", "params", method, objects);
        };
    }
}
