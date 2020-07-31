package com.example.wby.plugin.frame.exception;

/**
 * @Description
 * @Date 2020/7/31 17:11
 * @Author wuby31052
 */
public class SystemException extends RuntimeException {
    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = -1944973629369163147L;

    public SystemException(Throwable cause) {
        super(cause);
    }

    public static void build(Throwable cause) {
        throw new SystemException(cause);
    }

}
