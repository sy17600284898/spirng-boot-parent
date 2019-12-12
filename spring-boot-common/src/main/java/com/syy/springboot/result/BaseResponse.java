package com.syy.springboot.result;


/**
 * BaseResponse
 *
 * @param <T>
 * @author: Lenovo
 * @date: 2018-11-09 12:02:00
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
public class BaseResponse<T> {
	private boolean result = true;
	/**
	 * 成功或失败时返回的错误信息
	 */
	private String message;
	/**
	 * 成功时返回的数据信息
	 */
	private T data;
	/**
	 * 返回的代码，0表示成功，其他表示失败
	 */
	private int code;

	public BaseResponse() {
	}

	public BaseResponse(T data) {
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorMessage(String message) {
		this.result = false;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BaseResponse(boolean result, int code, String msg, T data) {
		this.result=result;
		this.code = code;
		this.message = msg;
		this.data = data;
	}

	public BaseResponse(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public BaseResponse(boolean result, ResultStatusCode resultStatusCode, T data) {
		this(result, resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
	}

	public BaseResponse(boolean result, ResultStatusCode resultStatusCode) {
		this(result, resultStatusCode, null);
	}

	public BaseResponse(ResultStatusCode resultStatusCode, String msg) {
		this(resultStatusCode.getCode(), msg);
	}
}