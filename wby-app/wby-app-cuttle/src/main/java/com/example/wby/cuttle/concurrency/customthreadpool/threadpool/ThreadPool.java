package com.example.wby.cuttle.concurrency.customthreadpool.threadpool;


import com.example.wby.cuttle.concurrency.customthreadpool.taskpool.ITask;
import com.example.wby.cuttle.concurrency.customthreadpool.taskpool.TaskPool;
import com.example.wby.cuttle.concurrency.customthreadpool.util.DateUtil;
import com.example.wby.cuttle.concurrency.customthreadpool.util.ThreadPoolException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池
 *
 * @Author wubinyu
 * @Date 2017/12/25 15:47.
 */
public class ThreadPool {
    /**
     * 最多的线程数量
     */
    private int maxNumThreadSize;
    /**
     * 最少的线程数量
     */
    private int minNumThreadSize;
    /**
     * 线程池维护线程所允许的空闲时间 单位（秒）
     */
    private int keepAliveTime;

    /**
     * 任务池
     * 按照优先级来进行存储
     */
    private TaskPool<ITask> taskPool = new TaskPool<>();

    /**
     * 线程安全的线程队列
     */
    private BlockingQueue<WorkThread> workThreadQuery = new LinkedBlockingQueue<>();

    /**
     * 执行任务队列的时候，wait() 与 notifyAll()的监视对象
     */
    private static final String EXECUTE_TASK_LOCK = new String("executeTaskLock");

    /**
     * 空闲线程的Id存放在此
     */
    private ConcurrentMap<String, String> threadFreeMap = new ConcurrentHashMap<>();

    /**
     * 定时维护线程池
     */
    private Timer maintainTimer;

    public ThreadPool() {
        this.maxNumThreadSize = 7;
        this.minNumThreadSize = 3;
        this.keepAliveTime = 30 * DateUtil.DATE_SECOND;
        loadWorkThreadQuery();
        maintainTimer = new Timer();
    }

    public ThreadPool(int maxNumThreadSize, int minNumThreadSize, int keepAliveTime) throws ThreadPoolException {
        if (minNumThreadSize < 1) {
            throw new ThreadPoolException("线程池中不能没有线程");
        }
        if (maxNumThreadSize < minNumThreadSize) {
            throw new ThreadPoolException("最多的线程数不能少于最少的线程数");
        }
        if (keepAliveTime < 1) {
            throw new ThreadPoolException("线程池维护线程所允许的空闲时间不能少于1秒");
        }
        this.maxNumThreadSize = maxNumThreadSize;
        this.minNumThreadSize = minNumThreadSize;
        this.keepAliveTime = keepAliveTime;
        loadWorkThreadQuery();
        maintainTimer = new Timer();
    }

    /**
     * 加载最少数量的工作线程
     */
    private void loadWorkThreadQuery() {
        for (int i = 0; i < minNumThreadSize; i++) {
            new WorkThread();
        }
    }

    /**
     * 工作线程  内部类
     */
    private class WorkThread extends Thread {
        private String ID;
        private boolean isWorking;
        private boolean isDied;

        public WorkThread() {
            ID = "" + hashCode();
            workThreadQuery.add(this);
            addThreadFreeNum(this.getID());
            System.out.println("线程ID:" + ID + "新线程被添加、" + "空闲线程数" + threadFreeMap.size() + "、总的线程数" + workThreadQuery.size() + "、未处理的任务数:" + taskPool.taskSize());
        }

        @Override
        public void run() {
            while (!isDied) {
                while (taskPool.taskSize() < 1 && !isDied) {
                    System.out.println(Thread.currentThread().getName()+"发现没有任务！睡眠zzzzzzzz");
                    setStopWorking();
                    try {
                        synchronized (EXECUTE_TASK_LOCK) {
                            EXECUTE_TASK_LOCK.wait();
                            System.out.println(Thread.currentThread().getName() + "被唤醒");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (taskPool.taskSize() > 0 && !isDied) {
                    System.out.println(Thread.currentThread().getName() + "有任务执行，任务总数：" + taskPool.taskSize());
                    setStartWorking();
                    ITask task = taskPool.removeTask();
                    try {
                        if (task != null) {
                            task.startWork();
                        }
                    } catch (Exception e) {
                        try {
                            taskPool.rejoinTask(task);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName()+"死亡");
        }

        public void setStopWorking() {
            this.isWorking = false;
            // TODO: 2017/12/25 加入空闲线程
            addThreadFreeNum(this.getID());
        }

        public void setStartWorking() {
            this.isWorking = true;
            // TODO: 2017/12/25 移除空闲线程
            subtractThreadFreeNum(this.getID());
        }

        public void setDied(boolean isDied) {
            this.isDied = isDied;
            workThreadQuery.remove(this);
            subtractThreadFreeNum(this.getID());
            synchronized (EXECUTE_TASK_LOCK) {
                EXECUTE_TASK_LOCK.notifyAll();
            }
        }

        public String getID() {
            return ID;
        }
        public void setID(String id) {
            ID= id;
        }

    }

    /**
     * 执行线程，start并延迟启动守护线程池
     */
    public void execute() {
        for (WorkThread workThread : workThreadQuery) {
            workThread.start();
        }
        maintainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
               // maintainPool();
            }
        },3000,keepAliveTime);
    }

    /**
     * 添加任务
     * @param task
     * @throws Exception
     */
    public void addTask(ITask task) throws Exception{
        taskPool.addTask(task);
        synchronized(EXECUTE_TASK_LOCK){
            EXECUTE_TASK_LOCK.notifyAll();
        }
    }

    /**
     * 加入空闲线程map中
     * @param id
     */
    public synchronized void addThreadFreeNum(String id) {
        this.threadFreeMap.put(id, id);
    }

    /**
     * 从空闲线程map中移除
     * @param id
     */
    public synchronized void subtractThreadFreeNum(String id) {
        this.threadFreeMap.remove(id, id);
    }

    public int getMaxNumThreadSize() {
        return maxNumThreadSize;
    }

    public void setMaxNumThreadSize(int maxNumThreadSize) {
        this.maxNumThreadSize = maxNumThreadSize;
    }

    public int getMinNumThreadSize() {
        return minNumThreadSize;
    }

    public void setMinNumThreadSize(int minNumThreadSize) {
        this.minNumThreadSize = minNumThreadSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    @Override
    public String toString() {
        return "ThreadPool{" +
                "maxNumThreadSize=" + maxNumThreadSize +
                ", minNumThreadSize=" + minNumThreadSize +
                ", keepAliveTime=" + keepAliveTime +
                ", taskPool=" + taskPool +
                ", workThreadQuery=" + workThreadQuery +
                ", threadFreeMap=" + threadFreeMap +
                ", maintainTimer=" + maintainTimer +
                '}';
    }
}
