package com.example.wby.cuttle.businesslog.service;

import com.example.wby.cuttle.businesslog.entity.OperationLogEntity;

/**
 * @author wubinyu
 */
public interface ILogHelper {

    String getRemoteIp();

    String getUserId();

    default String getUserName() {return "";}

    String getMac();

    String getServiceId();

    String getComponentId();

    default String getUserOrgId(){return null;}

    default String getUserOrgName() {return null;}

    void insertLog(OperationLogEntity logEntity);

}
