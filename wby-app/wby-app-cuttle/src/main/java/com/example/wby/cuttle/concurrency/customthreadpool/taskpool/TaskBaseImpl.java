package com.example.wby.cuttle.concurrency.customthreadpool.taskpool;

/**
 * @Author wubinyu
 * @Date 2017/12/25 15:07.
 */
public abstract class TaskBaseImpl implements ITask {
    public int maxAgainExecuteNum;


    @Override
    public abstract void startWork() throws Exception;

    @Override
    public int getPriority() throws Exception {
        return ITask.NORM_PRIORITY;
    }

    @Override
    public int getMaxAgainExecuteNum() {
        return maxAgainExecuteNum;
    }
}
