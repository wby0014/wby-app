package com.example.wby.common.ex;


import com.example.wby.common.log.errorcode.IErrorCode;


public class BaseRuntimeException extends RuntimeException {

    private String code;
    private Object[] params;

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseRuntimeException(Throwable cause, String code, String message) {
        super(message, cause);
        this.code = code;
    }

    public BaseRuntimeException(String code, String message, Object... params) {
        super(message);
        this.code = code;
        this.params = params;
    }

    public BaseRuntimeException(Throwable cause, String code, String message, Object... params) {
        super(message, cause);
        this.code = code;
        this.params = params;
    }

    public BaseRuntimeException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BaseRuntimeException(IErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
    }

    public BaseRuntimeException(IErrorCode errorCode, Object[] args) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        if (null != args) {
            this.params = (Object[]) args.clone();
        }

    }

    public BaseRuntimeException(IErrorCode errorCode, Object[] args, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        if (null != args) {
            this.params = (Object[]) args.clone();
        }

    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getParams() {
        return null == this.params ? null : this.params.clone();
    }

    public void setParams(Object[] params) {
        if (null == params) {
            this.params = null;
        } else {
            this.params = params.clone();
        }
    }
}
