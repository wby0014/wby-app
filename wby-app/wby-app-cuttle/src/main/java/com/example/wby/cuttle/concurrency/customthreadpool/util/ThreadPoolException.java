package com.example.wby.cuttle.concurrency.customthreadpool.util;

/**
 * 线程池的自定义异常
 * @Author wubinyu
 * @Date 2017/11/13 19:25.
 */
public class ThreadPoolException extends Exception {
    public ThreadPoolException(){
        super();
    }
    public ThreadPoolException(String message){
        super(message);
    }
    public ThreadPoolException(String message,Throwable cause){
        super(message,cause);
    }
    public ThreadPoolException(Throwable cause){
        super(cause);
    }
}
