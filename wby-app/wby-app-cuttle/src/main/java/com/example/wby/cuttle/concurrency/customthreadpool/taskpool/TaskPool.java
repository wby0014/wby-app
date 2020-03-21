package com.example.wby.cuttle.concurrency.customthreadpool.taskpool;


import com.example.wby.cuttle.concurrency.customthreadpool.util.ThreadPoolException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 任务池
 *
 * @Author wubinyu
 * @Date 2017/12/25 15:16.
 */
public class TaskPool<T extends ITask> {

    /**
     * 线程安全的任务map集合
     */
    private ConcurrentMap<Integer, BlockingQueue<T>> taskMap;
    /**
     * 重发记录表，任务执行失败后，再次加入任务队列的时候，先在这里记录
     */
    private ConcurrentMap<T,Integer> rejoinTaskMap;

    public TaskPool() {
        this.taskMap = new ConcurrentHashMap<>();
        this.taskMap.put(ITask.MAX_PRIORITY, new LinkedBlockingQueue<T>());
        this.taskMap.put(ITask.NORM_PRIORITY, new LinkedBlockingQueue<T>());
        this.taskMap.put(ITask.MIN_PRIORITY, new LinkedBlockingQueue<T>());
        this.rejoinTaskMap = new ConcurrentHashMap<>();
    }

    public int taskSize() {
        return this.taskMap.get(T.MAX_PRIORITY).size()
                +this.taskMap.get(T.NORM_PRIORITY).size()
                +this.taskMap.get(T.MIN_PRIORITY).size();
    }

    public void addTask(T task) throws Exception {
        if (this.taskMap.containsKey(task.getPriority())) {
            this.taskMap.get(task.getPriority()).add(task);
            return;
        }
        throw new ThreadPoolException("优先级的值设置有误");
    }

    /**
     * 获取任务从优先级高的开始
     * @return
     */
    public synchronized ITask removeTask() {
        if (this.taskMap.get(T.MAX_PRIORITY).size() > 0) {
            return this.taskMap.get(T.MAX_PRIORITY).remove();
        }
        if (this.taskMap.get(T.NORM_PRIORITY).size() > 0) {
            return this.taskMap.get(T.NORM_PRIORITY).remove();
        }
        if (this.taskMap.get(T.MIN_PRIORITY).size() > 0) {
            return this.taskMap.get(T.MIN_PRIORITY).remove();
        }
        return null;
    }

    /**
     * 当任务执行失败后，再次将他加入任务池
     * @param task
     * @throws Exception
     */
    public synchronized void rejoinTask(T task) throws Exception {
        //如果再次执行的map集合没有该任务那么就添加进去
        if (!rejoinTaskMap.containsKey(task)) {
            rejoinTaskMap.put(task, 1);
        }
        //如果该任务的再次执行的次数大于他设置的值，那么就将其移除出再次执行的map集合
        if (rejoinTaskMap.get(task) > task.getMaxAgainExecuteNum()) {
            rejoinTaskMap.remove(task);
            return;
        }
        //否则的话，将其加一，再加入任务队列
        else {
            rejoinTaskMap.put(task, rejoinTaskMap.get(task) + 1);
        }
        this.taskMap.get(task.getPriority()).add(task);
    }
}
