package cn.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.beans.Message;
import cn.dao.MessageDao;
import cn.util.AccessTokenTool;
import cn.util.Constants;
import cn.util.HttpUtils;
import cn.util.SpringContext;
import cn.util.WXMessageUtil;

public class TextMessageSendThread implements Runnable{
	private MessageDao  messageDao=(MessageDao)SpringContext.getBean("messageDao");
	private Message message;
    public TextMessageSendThread(Message message)
    {
    	this.message=message;
    }

	public void run() 
	{
		String messageBody=WXMessageUtil.getTextJson(message.getToUser(), message.getContext());
		String re=HttpUtils.httpsPostJson(Constants.SendMessageUrl, messageBody);
		if(!"".equals(re))
		{
			String newState="0";
			JsonObject jsonMsg = null;
			jsonMsg =(JsonObject)new JsonParser().parse(re);
			String status=jsonMsg.get("errmsg").getAsString();
			if("ok".equals(status))
			{
				newState="1";
			}
			else
			{
				newState=jsonMsg.get("errcode").getAsString();
				//log.error("发送文本信息失败，code:"+errorCode);
				if(newState.equals("43001"))
				{
					AccessTokenTool.refreshAccessToken();
				}
			}
			try{
			messageDao.updateState(newState, message.getMid());
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
    

}
