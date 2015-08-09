package com.hannuus.core.json;

//import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hannuus.core.json.StringFormatSerializer;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class JsonVo<T> {
	
//	@JsonProperty("ERRCODE")
	private String errcode;
	
//	@JsonProperty("ERRMSG")
	private String errmsg;
	
//	@JsonProperty("STATUS")
	private String status;
	
	@JsonSerialize(using=StringFormatSerializer.class)
	private Integer total;
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 返回的数据
	 */
//	@JsonProperty("RESULT")
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
}
