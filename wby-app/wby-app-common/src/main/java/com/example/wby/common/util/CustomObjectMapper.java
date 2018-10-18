package com.example.wby.common.util;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class CustomObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;
    private final static String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";


    public CustomObjectMapper() {
        super();
        // 排除值为空属性
//        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //转换成对象时，没有属性的处理，忽略掉
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        // 进行缩进输出
        // configure(SerializationFeature.INDENT_OUTPUT, true);
        // 进行日期格式化
//        if (dateFormatPattern != null) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        setDateFormat(dateFormat);
//        }


        // 提供其它默认设置
     /*	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

     	objectMapper.setSerializationInclusion(Include.NON_NULL);
     		
     	objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));*/
    }
}
