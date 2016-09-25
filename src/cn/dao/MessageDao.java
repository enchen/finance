package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.beans.Message;
@Repository("messageDao")
public interface MessageDao {
	void newTextMessage(Message message); 
	List<Message> getUnSend();
	void updateState(String newstate,String mid);

}
