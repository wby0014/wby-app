package com.example.wby.cuttle.future;

/**
 * @Description
 * @Date 2020/11/5 11:00
 * @Author wuby31052
 */
public class RunnableDemo {

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("runnable test");
        }
    }

    public static void main(String[] args) {
        RunnableDemo demo = new RunnableDemo();
        Task task = demo.new Task();
        new CallableDemo().executorService.execute(task);
        System.out.println("end");
    }
}
