package com.example.wby.cuttle.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Date 2020/11/5 11:00
 * @Author wuby31052
 */
public class FutureTaskDemo {

    public static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }

    public static void main(String[] args) {

        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);
        executor.shutdown();

        // 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
       /* Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("主线程在执行任务");
        try {
            if (futureTask.get() != null) {
                System.out.println("task运行结果" + futureTask.get());
            } else {
                System.out.println("future.get()未获取到结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");
    }

}

