package com.example.wby.cuttle.concurrency.customthreadpool.taskpool;

/**
 * 任务接口
 * @Author wubinyu
 * @Date 2017/12/25 14:54.
 */
public interface ITask {

    /**
     * 最高优先级
     */
    int MAX_PRIORITY = 10;
    /**
     * 默认优先级
     */
    int NORM_PRIORITY = 5;
    /**
     * 最低优先级
     */
    int MIN_PRIORITY = 1;

    /**
     * 执行具体的逻辑方法
     * @throws Exception
     */
    void startWork() throws Exception;

    /**
     * 返回优先级
     * @return
     * @throws Exception
     */
    int getPriority() throws Exception;

    /**
     * 允许再次执行的次数，任务执行失败后，可以再次执行不多于此的次数
     * @return
     */
    int getMaxAgainExecuteNum();
}

