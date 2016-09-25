package cn.services;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.beans.Message;

public class MessageSendThread extends Thread {
	
	private List<Message> messages;
	public MessageSendThread(List<Message> messages)
	{
		this.messages=messages;
	}
	
	public void run()
	{
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for(Message mes:messages)
		{
			if(mes!=null)
			{
				Runnable t=new TextMessageSendThread(mes);
				pool.execute(t);
			}
		}
		pool.shutdown();
		
	}

}
