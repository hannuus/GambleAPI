package com.hannuus.gamble.comm;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.gamble.web.exception.GambleException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonVo<T> {
	
	@JsonProperty("ERRCODE")
	private String errcode;
	
	@JsonProperty("ERRMSG")
	private String errmsg;
	
	@JsonProperty("STATUS")
	private String status = JsonResultStatus.Success.getValue();
	
	@JsonProperty("TOTAL")
	private Integer total;
	
	public JsonVo(Exception ex) {
		if (ex instanceof GambleException) {
			GambleException gex = (GambleException)ex;
			this.errcode = gex.getCode();
			this.errmsg = gex.getReasoning();
		} else if (ex instanceof PushClientException) {
			this.errcode = GambleAPIErrorCode.PushClient.getCode();
			this.errmsg = GambleAPIErrorCode.PushClient.getReasoning() + " Details:" + getStackTrace(ex);
		}  else if (ex instanceof PushServerException) {
			this.errcode = GambleAPIErrorCode.PushServer.getCode();
			this.errmsg = GambleAPIErrorCode.PushServer.getReasoning() + " Details:" + getStackTrace(ex);
		} else {
			this.errcode = GambleAPIErrorCode.UnKnowException.getCode();
			this.errmsg = getStackTrace(ex);
		}
		this.status = JsonResultStatus.Failed.getValue();
	}
	
	public static String getStackTrace(Throwable throwable) {  
	    if(throwable==null) {
	    	return "";  
	    }
	    String stackTrace = throwable.getStackTrace().toString();  
	    try {  
	        Writer writer = new StringWriter();  
	        PrintWriter printWriter = new PrintWriter(writer);  
	        throwable.printStackTrace(printWriter);       
	        printWriter.flush();  
	        writer.flush();  
	        stackTrace = writer.toString();  
	        printWriter.close();              
	        writer.close();  
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }  
	    return stackTrace;  
	}
	
	public JsonVo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

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
