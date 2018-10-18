package com.example.wby.common.ex;


public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;



	public BaseRuntimeException(String message){
		super(message);
	}

	public BaseRuntimeException(String message, String  code){
		super(message);
		this.code = code;
	}

	public BaseRuntimeException(String message, Throwable t){
		super(message, t);
	}




	public BaseRuntimeException(){
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
