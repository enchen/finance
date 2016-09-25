package cn.services.impl;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.services.WXMessageService;
import cn.util.AccessTokenTool;
import cn.util.Constants;
import cn.util.HttpUtils;
import cn.util.WXMessageUtil;

public class WXMessageServiceImpl implements WXMessageService {
	private Logger log=Logger.getLogger(this.getClass());
	@Override
	public String sendTextMessage(String mes, String userid) {
		String messageBody=WXMessageUtil.getTextJson(userid, mes);
		String re=HttpUtils.httpsPostJson(Constants.SendMessageUrl, messageBody);
		if(!"".equals(re))
		{
			JsonObject jsonMsg = null;
			jsonMsg =(JsonObject)new JsonParser().parse(re);
			String status=jsonMsg.get("errmsg").getAsString();
			if("ok".equals(status))
			{
				
			}
			else
			{
				String errorCode=jsonMsg.get("errcode").getAsString();
				log.error("发送文本信息失败，code:"+errorCode);
				if(errorCode.equals("43001"))
				{
					AccessTokenTool.refreshAccessToken();
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
