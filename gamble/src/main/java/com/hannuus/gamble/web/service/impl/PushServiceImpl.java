package com.hannuus.gamble.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.domain.PushMessage;
import com.hannuus.gamble.web.service.PushService;

/**
 * 消息推送实现(暂提供Android，以后补充IOS)
 * 
 * @author cuesky
 * @date 2015年8月15日 下午7:12:31
 */
@Service
public class PushServiceImpl implements PushService {

	private Logger logger = Logger.getLogger(getClass());

	private PushKeyPair pair;
	private BaiduPushClient client;

	public PushServiceImpl() {
		init();
	}

	/**
	 * 初始化BaiduPushClient
	 */
	private void init() {
		pair = new PushKeyPair(R.baidupush.api_key, R.baidupush.secret_key);
		client = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		client.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
	}

	@Override
	public void pushMsgToSingleUser(PushMessage msg, Long userId)
			throws PushClientException, PushServerException {
		PushBatchUniMsgRequest request = new PushBatchUniMsgRequest();
		String[] channelIds = null;
		request.addChannelIds(channelIds);
		request.addMsgExpires(R.baidupush.msg_expires);
		request.addMessageType(R.baidupush.msg_type_notification);
		request.addDeviceType(R.baidupush.device_type_android);
		request.addMessage(msg.toString());

		PushBatchUniMsgResponse response = client.pushBatchUniMsg(request);
		if (logger.isDebugEnabled()) {
		logger.debug("msgId: " + response.getMsgId() + ",sendTime: "
				+ response.getSendTime());
		}
	}

	@Override
	public void pushMsgToAll(PushMessage msg) throws PushClientException,
			PushServerException {
		PushMsgToAllRequest request = new PushMsgToAllRequest();
		request.addMsgExpires(R.baidupush.msg_expires);
		request.addMessageType(R.baidupush.msg_type_notification);
		request.addDeviceType(R.baidupush.device_type_android);
		request.addMessage(msg.toString());

		PushMsgToAllResponse response = client.pushMsgToAll(request);
		if (logger.isDebugEnabled()) {
		logger.debug("msgId: " + response.getMsgId() + ",sendTime: "
				+ response.getSendTime());
		}
	}

	@Override
	public void pushMsgToTag(PushMessage msg, String tagName)
			throws PushClientException, PushServerException {
		PushMsgToTagRequest request = new PushMsgToTagRequest();
		request.addMsgExpires(R.baidupush.msg_expires);
		request.addMessageType(R.baidupush.msg_type_notification);
		request.addDeviceType(R.baidupush.device_type_android);
		request.addMessage(msg.toString());

		request.addTagName(tagName);
		PushMsgToTagResponse response = client.pushMsgToTag(request);
		if (logger.isDebugEnabled()) {
			logger.debug("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());
		}
	}

}
