package com.hannuus.gamble.web.exception;

import org.codehaus.jackson.annotate.JsonProperty;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.gamble.comm.GambleAPIErrorCode;

public class ErrorMessage {
	
	@JsonProperty("ERRCODE")
	private String errcode;
	
	@JsonProperty("ERRMSG")
	private String errmsg;
	
	@JsonProperty("STATUS")
	private String status;
	

	public ErrorMessage(Exception ex) {
		if (ex instanceof GambleException) {
			GambleException gex = (GambleException)ex;
			this.errcode = gex.getCode();
			this.errmsg = gex.getReasoning();
		} else if (ex instanceof PushClientException) {
			this.errcode = GambleAPIErrorCode.PushClient.getCode();
			this.errmsg = GambleAPIErrorCode.PushClient.getReasoning() + " 详细:" + getStackTrace(ex);
		}  else if (ex instanceof PushServerException) {
			this.errcode = GambleAPIErrorCode.PushServer.getCode();
			this.errmsg = GambleAPIErrorCode.PushServer.getReasoning() + " 详细:" + getStackTrace(ex);
		} else {
			this.errcode = GambleAPIErrorCode.UnKnowException.getCode();
			this.errmsg = getStackTrace(ex);
		}
		this.status = JsonResultStatus.Failed.getValue();
	}
	
	private String getStackTrace(Exception ex) {
		StringBuilder bu = new StringBuilder();
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			bu.append(elements[i]);
			for (int j = 0; j < i; j++) {
				bu.append("\t");
			}
			bu.append("\n");
		}
		return bu.toString();
	}


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


	@Override
	public String toString() {
		return "ErrorMessage [errcode=" + errcode + ", errmsg=" + errmsg
				+ ", status=" + status + "]";
	}
}
