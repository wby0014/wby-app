package com.example.wby.plugin.frame.enums;

/**
 * @Description
 * @Date 2020/7/31 17:14
 * @Author wuby31052
 */
public enum ProcessTypeEnum {

    /**
     * 处理前置
     */
    PRE("pre"),
    /**
     * 处理后置
     */
    POST("post");

    private String value;

    ProcessTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
