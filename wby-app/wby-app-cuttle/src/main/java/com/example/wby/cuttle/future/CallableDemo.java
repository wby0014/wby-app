package com.example.wby.cuttle.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Description
 * @Date 2020/11/5 11:00
 * @Author wuby31052
 */
public class CallableDemo {

    public ExecutorService executorService;

    {
        executorService = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue(100), new ThreadFactoryBuilder().setNameFormat("callableDemo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static class Task implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算啦！");
            Thread.sleep(2000);

            for (int i = 0; i < 100; i++) {
                sum = sum + i;
            }
            System.out.println("Callable子线程计算结束！");
            return sum;
        }
    }


    public static void main(String[] args) {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        Task task = new Task();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(task);
        //关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if (future.get() != null) {
                //输出获取到的结果
                System.out.println("future.get()-->" + future.get());
            } else {
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }

}
