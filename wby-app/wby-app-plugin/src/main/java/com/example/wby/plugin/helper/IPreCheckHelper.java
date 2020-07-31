package com.example.wby.plugin.helper;

import com.example.wby.plugin.context.RequestContext;
import com.example.wby.plugin.frame.annotation.OrderCode;
import com.example.wby.plugin.frame.annotation.Process;
import com.example.wby.plugin.frame.enums.ProcessTypeEnum;
import com.example.wby.plugin.frame.model.BaseParam;
import com.example.wby.plugin.frame.model.PayParam;

/**
 * @Description
 * @Date 2020/7/31 17:28
 * @Author wuby31052
 */
public interface IPreCheckHelper {

    /**
     * 前置处理
     */
    @Process(type = ProcessTypeEnum.PRE, order = OrderCode.CHECK_ORDER)
    default void preRequest() {
        // 获取请求
        BaseParam request = (BaseParam) RequestContext.getContext().getRequest();
        PayParam payParam = preConvert(request);
        RequestContext.getContext().putBizData("KEY", "value");
    }

    /**
     * 子类自定义
     *
     * @param param
     * @return
     */
    PayParam preConvert(BaseParam param);

}
