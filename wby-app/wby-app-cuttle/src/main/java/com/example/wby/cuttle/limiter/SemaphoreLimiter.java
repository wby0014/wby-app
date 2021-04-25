package com.example.wby.cuttle.limiter;

import java.util.concurrent.Semaphore;

/**
 * @Description 并发度限流
 * @Date 2021/4/23 14:47
 * @Author wuby31052
 */
public class SemaphoreLimiter {

    private Semaphore semaphore = new Semaphore(10, true);

    public void process() {
        try {
            semaphore.acquire();
            // 业务处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }

}
