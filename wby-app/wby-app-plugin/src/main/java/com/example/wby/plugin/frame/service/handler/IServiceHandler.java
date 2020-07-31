package com.example.wby.plugin.frame.service.handler;

/**
 * @Description
 * @Date 2020/7/31 17:06
 * @Author wuby31052
 */
public interface IServiceHandler<P, R> {

    /**
     * 处理方法
     *
     * @param p
     * @return
     */
    R handle(P p);
}
