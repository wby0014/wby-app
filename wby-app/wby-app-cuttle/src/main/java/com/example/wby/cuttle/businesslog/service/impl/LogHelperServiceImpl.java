package com.example.wby.cuttle.businesslog.service.impl;

import com.example.wby.common.properties.IAgentService;
import com.example.wby.common.requestcontext.RequestContextUtil;
import com.example.wby.common.requestcontext.UserSession;
import com.example.wby.common.util.HttpRequestUtil;
import com.example.wby.cuttle.businesslog.entity.OperationLogEntity;
import com.example.wby.cuttle.businesslog.service.ILogHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wubinyu
 */
@Slf4j
public class LogHelperServiceImpl implements ILogHelper {

    @Autowired
    private IAgentService agentService;

    @Override
    public String getRemoteIp() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        if (request == null) {
            return "localhost";
        }
        return HttpRequestUtil.getRemoteIp(request);
    }

    /**
     * 操作者为用户时,填写用户名；
     * 操作者为系统内部任务时，填写执行任务的服务实例名,格式为“hik.组件标识.段标识.实例序号”
     *
     * @return
     */
    @Override
    public String getUserId() {

        String userIndexCode = "";
        UserSession userSession = RequestContextUtil.getUserSession();
        if (userSession != null) {
            userIndexCode = userSession.getUserId();
        }

        if (StringUtils.isEmpty(userIndexCode)) {
            return agentService.getTaskUserId();
        }
        return userIndexCode;
    }

    /**
     * 操作者为用户时，填写用户对于人员姓名；
     * 操作者为系统内部任务时，为空
     *
     * @return userName
     */
    @Override
    public String getUserName() {
        //单点登录后的session中无该字段，这里默认为空，如有需要，组件自己查询用户信息
        return null;
    }

    @Override
    public String getMac() {
        return null;
    }

    @Override
    public String getServiceId() {
        return agentService.getSegmentId();
    }

    @Override
    public String getComponentId() {
        return agentService.getComponentId();
    }

    @Override
    public void insertLog(OperationLogEntity log) {
        log.toString();
    }

}
