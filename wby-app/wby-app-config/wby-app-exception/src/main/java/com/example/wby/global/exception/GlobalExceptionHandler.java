package com.example.wby.global.exception;

import com.example.wby.common.ex.BaseRuntimeException;
import com.example.wby.common.result.ControllerResult;
import com.example.wby.common.util.LogUtils;
import com.example.wby.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @ExceptionHandler(value = BaseRuntimeException.class)
    public Object baseRuntimeErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("---BaseRuntimeException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        LogUtils.logException(e);
        BaseRuntimeException baseRuntimeException = (BaseRuntimeException) e;
        if (ObjectUtils.isEmpty((baseRuntimeException.getCode()))) {
            baseRuntimeException.setCode("-1");
        }
        return new ControllerResult(baseRuntimeException.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        LogUtils.logException(e);
        return new ControllerResult("-1", "服务器内部错误");
    }


}