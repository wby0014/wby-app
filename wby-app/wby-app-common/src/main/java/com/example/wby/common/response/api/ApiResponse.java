package com.example.wby.common.response.api;

public class ApiResponse<T> {
    private String code;
    private String msg;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }
}

