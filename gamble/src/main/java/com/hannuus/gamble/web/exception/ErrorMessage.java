package com.hannuus.gamble.web.exception;

import org.apache.commons.lang.StringUtils;
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
			this.errmsg = GambleAPIErrorCode.PushClient.getReasoning() + " 详细:" + ex.getMessage();
		}  else if (ex instanceof PushServerException) {
			this.errcode = GambleAPIErrorCode.PushServer.getCode();
			this.errmsg = GambleAPIErrorCode.PushServer.getReasoning() + " 详细:" + ex.getMessage();
		} else {
			this.errcode = GambleAPIErrorCode.UnKnowException.getCode();
			this.errmsg = ex.getMessage() == null ? StringUtils.join(ex.getStackTrace(), ",") : ex.getMessage();
		}
		this.status = JsonResultStatus.Failed.getValue();
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
