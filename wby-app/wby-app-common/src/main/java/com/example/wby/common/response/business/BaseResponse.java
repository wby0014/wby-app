package com.example.wby.common.response.business;

import com.example.wby.common.log.errorcode.IErrorCode;
import com.example.wby.common.util.ObjectUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "返回数据对象")
public class BaseResponse<T> {

    @ApiModelProperty(value = "错误码", example = "\"0\"")
    private String code = "0";
    @ApiModelProperty(value = "错误描述", example = "success")
    private String msg = "success";
    @ApiModelProperty("具体数据")
    private T data;

    public BaseResponse() {
        this.code = "0";
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(T data) {
        this.code = "0";
        this.data = data;
    }

    public BaseResponse(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public BaseResponse(IErrorCode errorCode, String appendMsg) {
        if (errorCode != null) {
            this.code = errorCode.getCode();
            String msg = errorCode.getMessage();
            if (ObjectUtils.isNotEmpty(appendMsg)) {
                msg = msg + ": " + appendMsg;
            }

            this.msg = msg;
        }
    }

    public String getCode() {
        return this.code;
    }

    public BaseResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public BaseResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }
}
