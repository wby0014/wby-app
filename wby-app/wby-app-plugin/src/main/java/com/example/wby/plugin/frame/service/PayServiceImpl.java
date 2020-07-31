package com.example.wby.plugin.frame.service;

import com.example.wby.plugin.context.RequestContext;
import com.example.wby.plugin.frame.controller.IPayService;
import com.example.wby.plugin.frame.model.BaseParam;
import com.example.wby.plugin.frame.model.PayParam;
import com.example.wby.plugin.frame.model.PayResult;
import com.example.wby.plugin.frame.service.handler.AbstractServiceHandler;
import com.example.wby.plugin.helper.IPreCheckHelper;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2020/7/31 17:20
 * @Author wuby31052
 */
@Service
public class PayServiceImpl extends AbstractServiceHandler<PayParam, PayResult> implements IPayService, IPreCheckHelper {

    @Override
    public PayResult process(PayParam param) {
        return handle(param);
    }

    @Override
    protected void doHandle() {
        // 获取接口入参
        PayParam param = (PayParam) RequestContext
                .getContext().getRequest();
        // TODO: 2020/7/31 写具体的业务逻辑代码

        PayResult result = new PayResult();
        // 设置响应
        RequestContext.getContext().setResult(result);
    }

    @Override
    public PayParam preConvert(BaseParam param) {
        return null;
    }

}
