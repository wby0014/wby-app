package com.example.wby.plugin.context;

import com.example.wby.plugin.frame.model.BaseParam;
import com.example.wby.plugin.frame.model.BaseResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2020/7/31 17:08
 * @Author wuby31052
 */
public class RequestContext {

    private static final ThreadLocal<RequestContext> LOCAL = new ThreadLocal<>();
    /**
     * 请求基类
     */
    private BaseParam baseParam;
    /**
     * 响应基类
     */
    private BaseResult baseResult;
    /**
     * 流程中断标识
     */
    private Boolean interrupt = false;
    /**
     * 业务数据
     */
    private Map<String, Object> dataMap = new HashMap<>();

    public RequestContext(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    public static RequestContext getContext() {
        return LOCAL.get();
    }

    public static void setContext(RequestContext context) {
        LOCAL.set(context);
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public void setRequest(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    public BaseParam getRequest() {
        return baseParam;
    }

    public BaseResult getResult() {
        return baseResult;
    }

    public void setResult(BaseResult baseResult) {
        this.baseResult = baseResult;
    }

    public Boolean isInterrupt() {
        return interrupt;
    }

    public void setInterrupt(Boolean interrupt) {
        this.interrupt = interrupt;
    }

    public Object getBizData(String key) {
        return dataMap.get(key);
    }

    public void putBizData(String key, Object value) {
        this.dataMap.put(key, value);
    }

}
