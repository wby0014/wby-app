package com.example.wby.plugin.frame.exception;

/**
 * @Description
 * @Date 2020/7/31 17:10
 * @Author wuby31052
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -1944973629369163147L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorMessage;

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static void build(Throwable cause) {
        if (cause instanceof BizException) {
            // 避免错误码丢失
            throw (BizException) cause;
        }
        throw new BizException(cause);
    }

    public static void build(String errorCode, String errorMessage) {
        throw new BizException(errorCode, errorMessage);
    }

    public static void build(String errorCode, String errorMessage, Throwable cause) {
        throw new BizException(errorCode, errorMessage, cause);
    }

}