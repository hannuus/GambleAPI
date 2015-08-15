package com.hannuus.gamble.web.service;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.hannuus.gamble.domain.PushMessage;

public interface PushService {

	/**
	 * 推送msg至单用户(其下所有Device)
	 * 
	 * @param msg
	 * @param channelId
	 * @throws PushServerException
	 * @throws PushClientException
	 */
	void pushMsgToSingleUser(PushMessage msg, Long userId)
			throws PushClientException, PushServerException;

	/**
	 * 推送msg至所有所有用户
	 * 
	 * @param msg
	 * @throws PushServerException
	 * @throws PushClientException
	 */
	void pushMsgToAll(PushMessage msg) throws PushClientException,
			PushServerException;

	/**
	 * 推送msg至tag所示用户
	 * 
	 * @param msg
	 * @param tagName
	 * @throws PushServerException
	 * @throws PushClientException
	 */
	void pushMsgToTag(PushMessage msg, String tagName)
			throws PushClientException, PushServerException;

}
