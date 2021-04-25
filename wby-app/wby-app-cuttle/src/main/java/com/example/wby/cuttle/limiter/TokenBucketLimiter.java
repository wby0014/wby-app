package com.example.wby.cuttle.limiter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description 令牌桶限流
 * @Date 2021/4/23 14:37
 * @Author wuby31052
 */
public class TokenBucketLimiter {
    /**
     * 速率是每秒两个许可
     */
    private RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static long permit = 50;

    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(2, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });


    /**
     * 控制访问速率
     *
     * @param tasks
     * @param executor
     */
    public void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable runnable : tasks) {
            rateLimiter.acquire();
            executor.execute(runnable);
        }
    }

    /**
     * 控制单位时间窗口内请求数,限制服务每秒的调用次数为50
     */
    public void getData() throws ExecutionException {
        long currentSeconds = System.currentTimeMillis() / 1000;
        if (counter.get(currentSeconds).incrementAndGet() > permit) {
            System.out.println("访问速率过快");
        }
        // 业务处理
    }
}
