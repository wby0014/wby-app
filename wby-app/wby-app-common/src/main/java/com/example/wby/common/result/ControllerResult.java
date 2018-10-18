package com.example.wby.common.result;

import io.swagger.annotations.ApiModelProperty;

public class ControllerResult {
	@ApiModelProperty( example = "200")
	private String code = "200";
	@ApiModelProperty(value = "请求描述", example = "请求成功")
	private String message = "";
	private Object data;

	public ControllerResult() {
		super();
	}

	public ControllerResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ControllerResult{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

}
