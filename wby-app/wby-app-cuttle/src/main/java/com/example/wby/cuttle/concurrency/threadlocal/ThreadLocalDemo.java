package com.example.wby.cuttle.concurrency.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 线程本地变量
 * 1.用作保存每个线程独享的对象，为每个线程都创建一个副本
 * 2.用作每个线程内需要独立保存信息，以便供其他方法更方便地获取该信息的场景,
 * 每个线程获取到的信息可能都是不一样的，前面执行的方法保存了信息后，
 * 后续方法可以通过ThreadLocal 直接获取到，避免了传参，类似于全局变量的概念(类似一般接口调用时会设置的请求上下文RequestContext).
 * @Date 2020/11/17 16:50
 * @Author wuby31052
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<ThreadLocalDemo> LOCAL_CONTEXT = new ThreadLocal<>();

    /**
     * 请求基类
     */
    private Object param;

    /**
     * 响应基类
     */
    private Object result;

    /**
     * 业务数据
     */
    private Map<String, Object> dataMap = new HashMap<>();

    public static void setContext(ThreadLocalDemo context) {
        LOCAL_CONTEXT.set(context);
    }

    public static ThreadLocalDemo getContext() {
        return LOCAL_CONTEXT.get();
    }

    public Object getBizData(String key) {
        return dataMap.get(key);
    }

    public void putBizData(String key, Object value) {
        this.dataMap.put(key, value);
    }

    public static void removeContext() {
        LOCAL_CONTEXT.remove();
    }

    public void setRequest(Object param) {
        this.param = param;
    }

    public Object getRequest() {
        return result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
