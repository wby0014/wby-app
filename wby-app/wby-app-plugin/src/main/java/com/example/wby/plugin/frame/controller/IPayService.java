package com.example.wby.plugin.frame.controller;

import com.example.wby.plugin.frame.model.PayParam;
import com.example.wby.plugin.frame.model.PayResult;

/**
 * @Description
 * @Date 2020/7/31 17:18
 * @Author wuby31052
 */
public interface IPayService {

    /**
     * 支付
     * @param param
     * @return
     */
    PayResult process(PayParam param);
}
