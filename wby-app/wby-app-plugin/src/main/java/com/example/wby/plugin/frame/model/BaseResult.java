package com.example.wby.plugin.frame.model;

import java.io.Serializable;

/**
 * @Description
 * @Date 2020/7/31 17:04
 * @Author wuby31052
 */
public class BaseResult implements Serializable {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
