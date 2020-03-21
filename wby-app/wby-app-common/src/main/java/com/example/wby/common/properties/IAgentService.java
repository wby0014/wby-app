package com.example.wby.common.properties;

import java.util.Properties;

/**
 * @author wubinyu
 */
public interface IAgentService {

    @Deprecated
    String getBicLocation();

    String getBicServiceDirectoryLocation();

    String getBicCasLocation();

    String getBicCenterCasLocation();

    String getBicBicLocation();

    String getBicLicenseLocation();

    String getBicLogLocation();

    String getBicUrl(String ipKey, String portKey, String protocolKey, String contextKey);

    String getLocalIndexCode();

    String getAgentNo();

    Integer getLocalIndex();

    Integer getSegmentIndex(String segmentId);

    Properties getInstallProperty();

    Properties getConfigProperty();

    String getInstallPropertyValue(String key);

    String getConfigPropertyValue(String key);

    String getConfigPropertyValue(String key, boolean decrypt);

    String getConfigPropertyValue(String segmentId, String suffix);

    String getConfigPropertyValue(String segmentId, String suffix, boolean decrypt);

    String getConfigPropertyValueAutoDecrypt(String segmentId, String suffix);

    String getConfigPropertyValueAutoDecrypt(String key);

    String getComponentId();

    String getSegmentId();

    String getLogLevel();

    void reloadProperty();

    String getTaskUserId();

    String getClientId(String segmentId, String instanceId);
}
