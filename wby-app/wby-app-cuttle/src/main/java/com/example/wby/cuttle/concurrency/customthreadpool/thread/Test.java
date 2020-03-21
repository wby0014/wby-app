package com.example.wby.cuttle.concurrency.customthreadpool.thread;

import com.example.wby.cuttle.concurrency.customthreadpool.taskpool.ITask;
import com.example.wby.cuttle.concurrency.customthreadpool.taskpool.TaskBaseImpl;
import com.example.wby.cuttle.concurrency.customthreadpool.threadpool.ThreadPool;

import java.util.Random;


/**
 *
 * Test
 * 测试类
 * @author yangchuang
 * @since 2014-3-15 上午11:03:23
 * @version 1.0.0
 * 修改记录
 * 修改日期    修改人    修改后版本    修改内容
 */
public class Test {
    public static void main(String[] args) throws Exception {


        /*int b =3;
        System.out.println(b >>(3-1)&1);
        System.out.println(b >>(2-1)&1);
        System.out.println(b >>(1-1)&1);*/



        ThreadPool pool = new ThreadPool(100, 10, 9);
        pool.execute();

        final Random r = new Random();

        System.out.println("加入失败重试的");
        for (int i = 0; i < 10; i++) {
            final int l = i;
            pool.addTask(new TaskBaseImpl() {

                @Override
                public int getMaxAgainExecuteNum() {
                    return 2;
                }
                @Override
                public void startWork() throws Exception {
                    int time = (int) (1000 * (1 + r.nextFloat()));
                    System.out.println(l + "-------------------------Start " + time);
                    Thread.sleep(time);
                    try{
                        int k=1/0;
                    }catch(Exception e){
                        throw new Exception(l+"失败重试：/0");
                    }
                    System.out.println(l + "-------------------------End " + time);
                }
            });
        }

        System.out.println("加入默认优先级的");
        for (int i = 30; i < 50; i++) {
            final int l = i;
            pool.addTask(new ITask() {
                @Override
                public void startWork() throws Exception {
                    int time = 1000 * (1 + r.nextInt(3));
                    System.out.println(l + "---2---Start " + time);
                    Thread.sleep(time);
                    System.out.println(l + "---2---End " + time);
                }

                @Override
                public int getPriority() throws Exception {
                    return NORM_PRIORITY;
                }

                @Override
                public int getMaxAgainExecuteNum() {
                    return 0;
                }
            });
        }

        System.out.println("加入最高优先级的");
        for (int i = 50; i < 100000; i++) {
            final int l = i;
            pool.addTask(new ITask() {
                @Override
                public void startWork() throws Exception {
                    int time = 1000 * (1 + r.nextInt(3));
                    System.out.println(l + "---3---Start " + time);
                    Thread.sleep(time);
                    System.out.println(l + "---3---End " + time);
                }

                @Override
                public int getPriority() throws Exception {
                    return MAX_PRIORITY;
                }

                @Override
                public int getMaxAgainExecuteNum() {
                    return 0;
                }
            });
        }


    }


}
