package com.example.wby.common.properties;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.wby.common.ex.BaseRuntimeException;
import com.example.wby.common.util.ObjectUtils;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service("agentService")
public class AgentServiceImpl implements IAgentService {

    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
    private Properties configProperty = null;
    private Properties installProperty = null;
    private static final String SEPARATOR = ".";
    private static final String TASK_USER_ID_PREFIX = "hik.";
    private static final int AUTO_DECRYPT_LENGTH = 36;
    @Resource(name = "agentConfigProperties")
    private AgentConfigProperty configProperties;
    @Value("${agent.encoding:utf-8}")
    private String encoding;
    private Map<String, Object> TEMP_CACHE = new HashMap();

    public AgentServiceImpl() {
    }

    @Override
    public String getBicLocation() {
        return this.getBicUrl("@bic.serviceDirectory.ip", "@bic.serviceDirectory.port", "@bic.serviceDirectory.protocol", "@bic.serviceDirectory.context");
    }

    @Override
    public String getBicServiceDirectoryLocation() {
        return this.getBicUrl("@bic.serviceDirectory.ip", "@bic.serviceDirectory.port", "@bic.serviceDirectory.protocol", "@bic.serviceDirectory.context");
    }

    @Override
    public String getBicCasLocation() {
        return this.getBicUrl("@bic.cas.ip", "@bic.cas.port", "@bic.cas.protocol", "@bic.cas.context");
    }

    @Override
    public String getBicCenterCasLocation() {
        return this.getBicUrl("@bic.centerCas.ip", "@bic.centerCas.port", "@bic.centerCas.protocol", "@bic.centerCas.context");
    }

    @Override
    public String getBicBicLocation() {
        return this.getBicUrl("@bic.bic.ip", "@bic.bic.port", "@bic.bic.protocol", "@bic.bic.context");
    }

    @Override
    public String getBicLicenseLocation() {
        return this.getBicUrl("@bic.license.ip", "@bic.license.port", "@bic.license.protocol", "@bic.license.context");
    }

    @Override
    public String getBicLogLocation() {
        return this.getBicUrl("@bic.log.ip", "@bic.log.port", "@bic.log.protocol", "@bic.log.context");
    }

    @Override
    public String getBicUrl(String ipKey, String portKey, String protocolKey, String contextkey) {
        String key = ipKey + portKey + protocolKey + contextkey;
        String value = (String) this.TEMP_CACHE.get(key);
        if (!StringUtils.isEmpty(value)) {
            if (logger.isDebugEnabled()) {
                logger.debug("get bicUrl use cache", new String[0]);
            }

            return value;
        } else {
            String ip = this.dealWithIp(this.getConfigPropertyValue(ipKey));
            String port = this.getConfigPropertyValue(portKey);
            String protocol = this.getConfigPropertyValue(protocolKey);
            String context = this.getConfigPropertyValue(contextkey);
            String location = protocol + "://" + ip + ":" + port + context;
            if (logger.isDebugEnabled()) {
                logger.debug("ipKey", "portKey", "protocolKey", "contextKey", "location", new Object[]{ipKey, portKey, protocolKey, contextkey, location});
            }

            this.TEMP_CACHE.put(key, location);
            return location;
        }
    }

    @Override
    public String getLocalIndexCode() {
        String prefix = this.getLocalConfigPrefix();
        if (StringUtils.isEmpty(prefix)) {
            logger.error("getLocalIndexCode error,prefix is error");
            throw new BaseRuntimeException("");
        } else {
            return this.getConfigPropertyValue(prefix + "." + "@indexCode");
        }
    }

    @Override
    public String getAgentNo() {
        return this.getConfigPropertyValue("@agent.agentNo");
    }

    @Override
    public Integer getLocalIndex() throws BaseRuntimeException {
        String segmentId = this.configProperties.getSegmentId();
        return this.getSegmentIndex(segmentId);
    }

    @Override
    public Integer getSegmentIndex(String segmentId) {
        Properties properties = this.getInstallProperty();
        if (properties != null && StringUtils.isNotEmpty(segmentId)) {
            String indexString = properties.getProperty(segmentId + "." + "@index");
            if (StringUtils.isNotEmpty(indexString)) {
                return Integer.valueOf(indexString);
            }
        }

        return null;
    }

    @Override
    public Properties getInstallProperty() {
        if (this.installProperty != null) {
            return this.installProperty;
        } else {
            Properties pps = new Properties();
            org.springframework.core.io.Resource res = ConfigLoadUtils.getResource(this.configProperties.getInstallPropertiesPath());
            if (res != null) {
                logger.info("getInstallProperty() configProperties.getInstallPropertiesPath()", new String[]{"path"}, this.configProperties.getInstallPropertiesPath());

                try {
                    InputStream in = null;
                    InputStreamReader inputStreamReader = null;

                    try {
                        in = res.getInputStream();
                        inputStreamReader = new InputStreamReader(in, this.getEncoding());
                        pps.load(inputStreamReader);
                    } catch (IOException var19) {
                        logger.error("getInstallProperty occur error", var19);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException var18) {
                                logger.warn("Close inputstream error when getInstallProperty", var18);
                            }
                        }

                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException var17) {
                                logger.warn("Close inputstream error when getInstallProperty", var17);
                            }
                        }

                    }
                } catch (Exception var21) {
                    logger.warn("getInstallProperty occur error", var21);
                }
            }

            this.installProperty = pps;
            return this.installProperty;
        }
    }

    @Override
    public Properties getConfigProperty() {
        if (this.configProperty != null) {
            return this.configProperty;
        } else {
            if (this.installProperty == null || this.installProperty.isEmpty()) {
                this.installProperty = this.getInstallProperty();
            }

            Properties pps = new Properties();
            String configPath = this.installProperty.getProperty("@componentPath") + this.configProperties.getConfigPropertiesPath();
            logger.info("getConfigProperty() configPath", new String[]{"path"}, configPath);

            try {
                InputStream in = null;
                InputStreamReader inputStreamReader = null;

                try {
                    in = new FileInputStream(configPath);
                    inputStreamReader = new InputStreamReader(in, this.getEncoding());
                    pps.load(inputStreamReader);
                } catch (IOException var19) {
                    logger.error("getConfigProperty occur error", var19);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException var18) {
                            logger.warn("Close inputstream error when getInstallProperty", var18);
                        }
                    }

                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException var17) {
                            logger.warn("Close inputstream error when getInstallProperty", var17);
                        }
                    }

                }
            } catch (Exception var21) {
                logger.warn("getConfigProperty occur error", var21);
            }

            this.configProperty = pps;
            return this.configProperty;
        }
    }

    @Override
    public String getComponentId() {
        return this.configProperties.getComponentId();
    }

    @Override
    public String getSegmentId() {
        return this.configProperties.getSegmentId();
    }

    @Override
    public String getLogLevel() {
        String segmentId = this.getSegmentId();
        if (StringUtils.isEmpty(segmentId)) {
            logger.error("getLogLevel error");
            throw new BaseRuntimeException("");
        } else {
            Integer index = this.getLocalIndex();
            return this.getConfigPropertyValue(segmentId + "." + index + "." + "@logLevel");
        }
    }

    @Override
    public void reloadProperty() {
        this.TEMP_CACHE.clear();
        this.installProperty = null;
        this.getInstallProperty();
        this.configProperty = null;
        this.getConfigProperty();
    }

    @Override
    public String getTaskUserId() {
        return "hik." + this.getComponentId() + "." + this.getSegmentId() + "." + this.getLocalIndex();
    }

    @Override
    public String getConfigPropertyValue(String segmentId, String suffix) {
        return this.getConfigPropertyValue(segmentId, suffix, false);
    }

    private List<String> getInstanceList(String segmentId) {
        String instanceStr = this.getConfigPropertyValue(segmentId + ".@instanceList");
        List<String> instanceList = Splitter.on(",").trimResults().splitToList(instanceStr);
        return instanceList;
    }

    @Override
    public String getConfigPropertyValue(String segmentId, String suffix, boolean decrypt) {
        String key = this.getKeyBySegmentIdAndSuffix(segmentId, suffix);
        return this.getConfigPropertyValue(key, decrypt);
    }

    private String getKeyBySegmentIdAndSuffix(String segmentId, String suffix) {
        Integer index = this.getSegmentIndex(segmentId);
        String key = "";
        if (index == null) {
            List<String> instanceList = this.getInstanceList(segmentId);
            if (ObjectUtils.isNotEmpty(instanceList)) {
                key = (String) instanceList.get(0) + "." + suffix;
            }
        } else {
            key = segmentId + "." + index + "." + suffix;
        }

        return key;
    }

    private String getEncoding() {
        if (this.encoding == null) {
            this.encoding = "utf-8";
        }

        return this.encoding;
    }

    private String dealWithIp(String ips) {
        String ip = null;
        if (StringUtils.isEmpty(ips)) {
            logger.info("dealWithIp ip isBlank", new String[0]);
            return null;
        } else {
            try {
                if (!ips.contains(",")) {
                    return ips;
                }

                String[] var3 = StringUtils.split(ips, ',');
                int var4 = var3.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    String tempIp = var3[var5];
                    InetAddress inetAddress = InetAddress.getByName(tempIp);
                    if (inetAddress.isReachable(2000)) {
                        ip = tempIp;
                        break;
                    }
                }
            } catch (Exception var8) {
                logger.warn("deal IP occur exception", var8);
            }

            return ip;
        }
    }

    @Override
    public String getConfigPropertyValue(String key) {
        return this.getConfigPropertyValue(key, false);
    }

    @Override
    public String getConfigPropertyValue(String key, boolean decrypt) {
        Properties property = this.getConfigProperty();
        String value = property.getProperty(key);
        if (!StringUtils.isEmpty(value) && decrypt) {
            try {
                //                return new String(AuthenticatorFactory.getAuthenticator("").decrypt(Base64.getDecoder().decode(value)), "utf-8");
                return null;
            } catch (Exception var6) {
                throw new BaseRuntimeException("");
            }
        } else {
            return value;
        }
    }

    @Override
    public String getConfigPropertyValueAutoDecrypt(String segmentId, String suffix) {
        String key = this.getKeyBySegmentIdAndSuffix(segmentId, suffix);
        return this.getConfigPropertyValueAutoDecrypt(key);
    }

    @Override
    public String getConfigPropertyValueAutoDecrypt(String key) {
        Properties property = this.getConfigProperty();
        String value = property.getProperty(key);
        if (StringUtils.isNotEmpty(value) && value.length() >= 36) {
            try {
                //                value = new String(AuthenticatorFactory.getAuthenticator().decrypt(Base64.getDecoder().decode(value)), "utf-8");
            } catch (Exception var5) {
                throw new BaseRuntimeException("");
            }
        }

        return value;
    }

    @Override
    public String getInstallPropertyValue(String key) {
        Properties property = this.getInstallProperty();
        return property.getProperty(key);
    }

    private String getLocalConfigPrefix() {
        Integer index = this.getLocalIndex();
        if (index != null) {
            String segmentId = this.configProperties.getSegmentId();
            return StringUtils.isEmpty(segmentId) ? null : segmentId + "." + index;
        } else {
            return null;
        }
    }

    public AgentConfigProperty getConfigProperties() {
        return this.configProperties;
    }

    public void setConfigProperties(AgentConfigProperty configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public String getClientId(String segmentId, String instanceId) {
        String clientId = this.getInstallPropertyValue(segmentId + "." + "@notify.@clientid");
        if (StringUtils.isEmpty(clientId)) {
            StringBuilder sb = new StringBuilder(64);
            if (StringUtils.isEmpty(instanceId)) {
                List<String> instanceList = this.getInstanceList(segmentId);
                if (ObjectUtils.isNotEmpty(instanceList)) {
                    instanceId = (String) instanceList.get(0);
                }
            }

            if (instanceId.startsWith(segmentId)) {
                instanceId = instanceId.substring(segmentId.length() + 1);
            }

            sb.append(this.getComponentId()).append(".").append(segmentId).append(".").append(this.getAgentNo()).append(".").append(instanceId);
            clientId = sb.toString();
        }

        return clientId;
    }
}
