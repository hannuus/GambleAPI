package com.hannuus.gamble.vo;

//import java.util.HashMap;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

//import com.hannuus.gamble.comm.JSONStatus;
//import com.hannuus.gamble.web.exception.ValidateException;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonVo<T> {
	
	@JsonProperty("ERRCODE")
	private String errcode;
	
	@JsonProperty("ERRMSG")
	private String errmsg;
	
	@JsonProperty("STATUS")
	private String status;

	/**
	 * 具体每个输入错误的消息
	 */
//	private HashMap<String, String> errors = new HashMap<String, String>();

	/**
	 * 返回的数据
	 */
	@JsonProperty("RESULT")
	private T result;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

//	public HashMap<String, String> getErrors() {
//		return errors;
//	}
//
//	public void setErrors(HashMap<String, String> errors) {
//		this.errors = errors;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
//
//	public void check() throws ValidateException {
//		if (this.getErrors().size() > 0) {
//			this.status = JSONStatus.Failed.getValue();
//			throw new ValidateException("Oops...Some Errors Happend.");
//		}
//	}
}
