package com.example.wby.cuttle.businesslog;

import com.example.wby.cuttle.businesslog.entity.OperationLogEntity;

import java.util.Date;

/**
 * @author wubinyu
 */
public class OperationLogUtils {

    private static ThreadLocal<OperationLogEntity> operationLocal = new ThreadLocal();

    public OperationLogUtils() {
    }

    public static OperationLogEntity getLog() {
        return (OperationLogEntity) operationLocal.get();
    }

    public static void createLog() {
        OperationLogEntity entity = new OperationLogEntity();
        entity.setResult("1");
        operationLocal.set(entity);
    }

    public static void setLog(OperationLogEntity entity) {
        operationLocal.set(entity);
    }

    public static void setResult(String result) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setResult(result);
        }

    }

    public static void setResultFail() {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setResult("0");
        }

    }

    public static void setActionDetail(String actionDetail) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setActionDetail(actionDetail);
        }

    }

    public static void setTraceId(String traceId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setTraceId(traceId);
        }

    }

    public static void setRelationId(String relationId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setRelationId(relationId);
        }

    }

    public static void setObjectId(String objectId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setObjectId(objectId);
        }

    }

    public static void setObjectName(String objectName) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setObjectName(objectName);
        }

    }

    public static void setObjectOrgName(String objectOrgName) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setObjectOrgName(objectOrgName);
        }

    }

    public static void setActionMessageId(String actionMessageId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setActionMessageId(actionMessageId);
        }

    }

    public static void setActionMultiLang(Integer actionMultiLang) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setActionMultiLang(actionMultiLang);
        }

    }

    public static void setComponentId(String componentId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setComponentId(componentId);
        }

    }

    public static void setServiceId(String serviceId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setServiceId(serviceId);
        }

    }

    public static void setOperationTime(Date date) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setOperationTime(date);
        }

    }

    public static void setUserId(String userId) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setUserId(userId);
        }

    }

    public static void setIp(String ip) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setIp(ip);
        }

    }

    public static void setMac(String mac) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setMac(mac);
        }

    }

    public static void setObjectType(String objectType) {
        if (operationLocal.get() != null) {
            ((OperationLogEntity) operationLocal.get()).setObjectType(objectType);
        }

    }

    public static OperationLogEntity remove() {
        OperationLogEntity entity = (OperationLogEntity) operationLocal.get();
        operationLocal.remove();
        return entity;
    }
}
