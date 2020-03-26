package com.example.wby.common.util;

import com.example.wby.common.ex.I18nUtils;
import com.example.wby.common.response.business.BaseResponse;
import com.example.wby.common.response.business.ListData;
import com.example.wby.common.response.business.PageData;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 错误码工具类：用于拼装完整的错误码信息
 */
public class ResponseUtil {

    private static final String I1N8_CODE_FORMAT = "errorCode.%s.description";

    /**
     * 拼装完整错误码信息：
     * 错误码为成功时，或该错误码为完整的错误码，则直接返回
     *
     * @param code
     *
     * @return PROFIX + code
     */
    public static String formatErrorCode(String code) {
        if ("0".equals(code) || code.startsWith("ServicePropertyUtil.getErrorcodeProfix()")) {
            return code;
        } else {
            return "ServicePropertyUtil.getErrorcodeProfix()" + code;
        }
    }

    /**
     * 根据错误码查找相应的多语言翻译：
     * 如：errorCode为 0x07b10001，则会找到 errorCode.0x07b10001.description=未知错误，请联系管理员
     * 返回等号后的错误信息
     *
     * @param code 错误码
     * @param message message
     *
     * @return 错误描述
     */
    public static String i18Format(String code, String message, Object[] args) {
        String codeKey = String.format(I1N8_CODE_FORMAT, code);
        String i18nMessage = I18nUtils.getMessage(codeKey);
        if (!codeKey.equals(i18nMessage)) {
            message = MessageFormat.format(i18nMessage, args);
        }
        return message;
    }


    /**
     *
     */
    public static <T> BaseResponse<ListData<T>> wrapperResponse(List<T> data) {
        ListData<T> listData = new ListData<>();
        listData.setList(data);
        listData.setTotal(Long.valueOf(data.size()));
        return new BaseResponse<>(listData);
    }

    /**
     *
     */
    public static <T> BaseResponse<PageData<T>> wrapperResponse(List<T> data, Integer pageNo, Integer pageSize, Long total) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotal(total);
        pageData.setPageNo(pageNo);
        pageData.setPageSize(pageSize);
        pageData.setList(data);
        return new BaseResponse<>(pageData);
    }


    /**
     * @param response 返回结果
     * @param data 列表数据
     */
    @Deprecated
    public static <T> BaseResponse<ListData<T>> setData(BaseResponse response, List<T> data) {
        ListData<T> listData = new ListData<>();
        listData.setList(data);
        listData.setTotal(Long.valueOf(data.size()));
        response.setData(listData);
        return response;
    }

    /**
     * @param response 返回结果
     * @param data 分页数据
     * @param pageNo 页码
     * @param pageSize 分页大小
     * @param total 总行数
     */
    @Deprecated
    public static <T> BaseResponse<ListData<T>> setData(BaseResponse response, List<T> data, Integer pageNo, Integer pageSize, Long total) {
        PageData<T> pageData = new PageData<>();
        pageData.setList(data);
        pageData.setTotal(total);
        pageData.setPageNo(pageNo);
        pageData.setPageSize(pageSize);
        response.setData(pageData);
        return response;
    }

    public static <T> PageData<T> generatePageData(List<T> list, Integer pageNo, Integer pageSize, Long total) {
        PageData<T> pageData = new PageData<>();
        pageData.setPageNo(pageNo);
        pageData.setPageSize(pageSize);
        pageData.setList(list);
        pageData.setTotal(total);
        return pageData;
    }

    /**
     * 返回list列表
     */
    public static <T> BaseResponse<ListData<T>> responseListData(List<T> list) {
        ListData<T> listData = new ListData<>();
        if (ObjectUtils.isNotEmpty(list)) {
            listData.setList(list);
            listData.setTotal((long) list.size());
            return new BaseResponse<>(listData);
        }
        listData.setTotal((long) 0);
        listData.setList(new ArrayList<>());
        return new BaseResponse<>(listData);
    }

}
