package com.example.wby.cuttle.businesslog.aop;

import com.example.wby.common.log.trace.TraceContext;
import com.example.wby.common.log.trace.TraceLocalUtil;
import com.example.wby.cuttle.businesslog.BusLogConstants;
import com.example.wby.cuttle.businesslog.OperationLogUtils;
import com.example.wby.cuttle.businesslog.annotation.OperationLog;
import com.example.wby.cuttle.businesslog.entity.OperationLogEntity;
import com.example.wby.cuttle.businesslog.service.ILogHelper;
import com.google.common.base.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author wubinyu
 */
@Aspect
@Component("bootLogAspectComponent")
public class OperationLogAspect {

    private ILogHelper logHelper;

    private Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);

    @Pointcut("@annotation(com.example.wby.cuttle.businesslog.annotation.OperationLog)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        OperationLogEntity entity = new OperationLogEntity();
        entity.setOperationTime(new Date());
        if (this.logHelper != null) {
            entity.setComponentId(this.logHelper.getComponentId());
            entity.setServiceId(this.logHelper.getServiceId());
            entity.setIp(this.logHelper.getRemoteIp());
            entity.setMac(this.logHelper.getMac());
            entity.setUserId(this.logHelper.getUserId());
            entity.setUserName(this.logHelper.getUserName());
            entity.setUserOrgId(this.logHelper.getUserOrgId());
            entity.setUserOrgName(this.logHelper.getUserOrgName());
        }

        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        OperationLog ano = (OperationLog)method.getAnnotation(OperationLog.class);
        if (ano != null) {
            entity.setAction(ano.action());
            entity.setModuleId(ano.moduleId());
            entity.setTerminalType(ano.terminalType());
            entity.setAction("log.action." + entity.getAction() + ".displayName");
            entity.setModuleId("log.moduleId." + entity.getModuleId() + ".displayName");
            String objectType = ano.objectType();
            if (!StringUtils.isEmpty(objectType)) {
                entity.setObjectType("log.objectType." + objectType + ".displayName");
            }

            String actionMessageId = ano.actionMessageId();
            String actionDetail = ano.actionDetail();
            if (!StringUtils.isEmpty(actionMessageId)) {
                entity.setActionMultiLang(BusLogConstants.MULTI_LANG_SUPPORT);
                entity.setActionMessageId("log.actionMessageId." + actionMessageId + ".message");
            } else if (!Strings.isNullOrEmpty(actionDetail)) {
                entity.setActionDetail(actionDetail);
            }

            TraceContext traceContext = TraceLocalUtil.getTraceContext();
            if (traceContext != null) {
                entity.setTraceId(traceContext.getTraceId());
            }
        }

        OperationLogUtils.setLog(entity);
        OperationLogUtils.setResult("1");
        Object o = joinPoint.proceed();
        OperationLogEntity log = OperationLogUtils.remove();

        try {
            logger.info(log.toString());
        } catch (Exception var9) {
            this.logger.error("write operation log error");
        }

        return o;
    }

    @AfterThrowing(
            pointcut = "controllerAspect()",
            throwing = "e"
    )
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        OperationLogUtils.setResult("0");
        OperationLogEntity log = OperationLogUtils.remove();

        try {
            logger.info(log.toString());
        } catch (Exception var5) {
            this.logger.error("write operation log error");
        }
    }

}
