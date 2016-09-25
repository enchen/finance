package cn.services;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.beans.Message;
import cn.dao.MessageDao;
import cn.dao.UserDao;
import cn.util.AccessTokenTool;





public class AutoTimerJob {
	private Logger log=Logger.getLogger(this.getClass());

@Autowired
private MessageDao messageDao;
	public void init()
	{}
	public void autoToken(){
	
		
		AccessTokenTool.refreshAccessToken();		
		log.info("token更新"+AccessTokenTool.getAccessToken());
		System.out.print("token更新"+AccessTokenTool.getAccessToken());
		
	}
	public void autoSend()
	{
		List<Message> messages=messageDao.getUnSend();
		if(messages!=null&&messages.size()>0)
		{
			new MessageSendThread(messages).start();
		}
	}



}
