package com.example.wby.util;

import com.example.wby.common.util.SpringContextUtil;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;

public class ActiveMqUtils {

	private static final  String brokerUrl;

	static {
		ActiveMQProperties configProperties = SpringContextUtil.getBean(ActiveMQProperties.class);
		brokerUrl  = configProperties.getBrokerUrl();
	}

	public static String getMqBrokerUrl(){
		return brokerUrl;
	}

}
