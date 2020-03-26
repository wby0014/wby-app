package com.example.wby.common.ex;

import com.example.wby.common.log.errorcode.IErrorCode;
import com.example.wby.common.response.business.BaseResponse;
import com.example.wby.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Description: 异常处理器。该类会处理所有在执行标有@RequestMapping注解的方法时发生的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * get请求的参数校验失败
     *
     * @param exception
     *
     * @return
     */
    @ExceptionHandler
    public BaseResponse handle(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        if (!CollectionUtils.isEmpty(violations)) {
            String message = violations.iterator().next().getMessage();
            String code = "0";
            String i18Msg = I18nUtils.getMessage(message);
            message = ResponseUtil.i18Format(code, message, new String[]{i18Msg});
            logger.error("validate error", "message", message);
            return new BaseResponse(code, message);
        }
        return new BaseResponse("1001", "msg");
    }

    /**
     * post请求校验失败
     *
     * @param exception
     *
     * @return
     */
    @ExceptionHandler
    public BaseResponse handle(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        String code = ResponseUtil.formatErrorCode("1002");
        String msg = null;
        if (!CollectionUtils.isEmpty(errors)) {
            int first = 0;
            for (int i = 0; i < errors.size(); i++) {
                ObjectError error = errors.get(i);
                String message = error.getDefaultMessage();
                String field = null;
                if (error instanceof FieldError) {
                    field = ((FieldError) error).getField();
                }

                String i18Msg = I18nUtils.getMessage(message);
                message = ResponseUtil.i18Format(code, message, new String[]{i18Msg});
                if (i == first) {
                    //取一个错误信息传给前端
                    msg = message;
                }
                logger.error("validate error", "field", "message", field, message);
            }
        }
        return new BaseResponse(code, msg);
    }

    @ExceptionHandler
    public BaseResponse handle(BaseRuntimeException exception) {
        //errorCode处理
        String code = exception.getCode();
        code = ResponseUtil.formatErrorCode(code);
        //多语言处理
        String message = exception.getMessage();
        message = ResponseUtil.i18Format(code, message, exception.getParams());
        logger.error(code, message);
        return new BaseResponse(code, message);
    }

    /**
     * 其他没有处理的错误
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler
    public BaseResponse<Object> handle(Exception e) {
        logger.error(e.getMessage());
        IErrorCode errorCode = null;

        String code = errorCode.getCode();
        code = ResponseUtil.formatErrorCode(code);
        //多语言处理
        String message = errorCode.getMessage();
        message = ResponseUtil.i18Format(code, message, null);
        //上传文件过大的错误
        if (e.getMessage().startsWith("Maximum upload size exceeded")) {
            code = "0x19111104";
            message = ResponseUtil.i18Format(code, "导入文件超过1M", null);
        }
        return new BaseResponse<Object>(code, message);
    }

}
