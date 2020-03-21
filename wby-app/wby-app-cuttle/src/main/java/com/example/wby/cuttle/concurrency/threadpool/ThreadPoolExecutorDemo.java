package com.example.wby.cuttle.concurrency.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 推荐使用自定义线程池
 * 参数含义：
 * *      corePoolSize : 线程池中常驻的线程数量。核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
 * *      maximumPoolSize : 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
 * *      keepAliveTime : 当线程数量多于 corePoolSize 时，空闲线程的存活时长，超过这个时间就会被回收
 * *      unit : keepAliveTime 的时间单位
 * *      workQueue : 存放待处理任务的队列，该队列只接收 Runnable 接口
 * *      threadFactory : 线程创建工厂
 * *      handler : 当线程池中的资源已经全部耗尽，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法，参考 ThreadPoolExecutor 类中的内部策略类
 *
 * @author wubinyu
 */
public class ThreadPoolExecutorDemo {

    private static ExecutorService threadPoolExecutor;

    ThreadPoolExecutorDemo(BlockingQueue blockingQueue) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("custom-pool-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, blockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());
        //        threadPoolExecutor = Executors.newSingleThreadExecutor();
        //        threadPoolExecutor = Executors.newCachedThreadPool();
        //        threadPoolExecutor = Executors.newFixedThreadPool(2);
        //        threadPoolExecutor = Executors.newScheduledThreadPool(3);
    }

    public static String test(int i) {
        return Thread.currentThread().getName() + " " + i;
    }

    public static void main(String[] args) {
        new ThreadPoolExecutorDemo(new LinkedBlockingQueue(100));

        /*for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + " " + finalI));
        }*/
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Future<String> future = threadPoolExecutor.submit(() -> test(finalI));
            try {
                list.add(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String v : list) {
            System.out.println(v);
        }
    }


}
