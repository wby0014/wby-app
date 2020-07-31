package com.example.wby.plugin.frame.service.handler;

import com.example.wby.plugin.context.RequestContext;
import com.example.wby.plugin.frame.enums.ProcessTypeEnum;
import com.example.wby.plugin.frame.exception.BizException;
import com.example.wby.plugin.frame.exception.SystemException;
import com.example.wby.plugin.frame.model.BaseParam;
import com.example.wby.plugin.frame.model.BaseResult;
import com.example.wby.plugin.frame.process.ProcessCache;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description
 * @Date 2020/7/31 17:05
 * @Author wuby31052
 */
public abstract class AbstractServiceHandler<P extends BaseParam, R extends BaseResult> implements IServiceHandler<P, R> {

    @Override
    public R handle(P p) {
        // 设置上下文
        RequestContext.setContext(new RequestContext(p));
        R r = null;
        try {
            preHandle();
            // 流程中断判断
            if (!RequestContext.getContext().isInterrupt()) {
                doHandle();
            }
            postHandle();
            // 响应
            r = (R) RequestContext.getContext().getResult();
        } catch (Throwable e) {
            if (e instanceof BizException) {
                // 明确的业务异常由开发者抛出，这里不做处理
                throw (BizException) e;
            } else if (e instanceof SystemException) {
                // 明确的系统异常由开发者抛出，这里不做处理
                throw (SystemException) e;
            } else {
                // 不明确的异常如空指针，这里转换为系统异常
                SystemException.build(e);
            }
        } finally {
            // 移除上下文
            RequestContext.removeContext();
        }
        return r;
    }

    /**
     * 业务处理前置 可以对request做必要逻辑
     */
    private void preHandle() throws Exception {
        runProcess(ProcessTypeEnum.PRE);
    }

    /**
     * 具体业务处理 response 应该由doHandle产生
     */
    protected abstract void doHandle();

    /**
     * 业务处理后置 可以对response做必要逻辑
     */
    private void postHandle() throws Exception {
        runProcess(ProcessTypeEnum.POST);
    }

    /**
     * 前置、后置执行
     *
     * @param processType
     */
    private void runProcess(ProcessTypeEnum processType) throws Exception {
        // method 默认升序排列
        List<Method> methods = ProcessCache.getProcessByType(this.getClass(), processType.getValue());
        try {
            for (Method method : methods) {
                // 流程中断
                if (RequestContext.getContext().isInterrupt()) {
                    break;
                }
                // 具体执行点
                method.invoke(this);
            }
        } catch (Exception e) {
            // 反射会包装应用抛出的异常
            Throwable t = e.getCause();
            if (t instanceof BizException) {
                throw (BizException) t;
            } else if (t instanceof SystemException) {
                throw (SystemException) t;
            }
            throw e;
        }
    }

}
