package cn.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.beans.Message;
import cn.beans.Spend;
import cn.beans.User;
import cn.dao.FinanceDao;
import cn.dao.MessageDao;
import cn.services.FinanceService;
import cn.util.RedisTool;
@Service
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	private FinanceDao financeDao;
	@Autowired
	private MessageDao messageDao;
	public int createNewSpend(Spend spend,String token)
	{
		int re=0;
		User user=RedisTool.getInstance().get(token);
		if(user!=null)
		{
			spend.setCreateId(user.getUserid());
			spend.setCreateTime(new Date());
			re= financeDao.createNewSpend(spend);
		}
		
		if(re==1)
		{
			Message mes=new Message();
			mes.setFromUser(spend.getCreateId());
			mes.setToUser(spend.getCheckId());
			mes.setType("text");
			mes.setContext(user.getName()+"提交了一条"+spend.getFee()+"元的报销申请，请查看详细情况并处理！");
			messageDao.newTextMessage(mes);
			mes.setFromUser("system");
			mes.setToUser(user.getUserid());
			mes.setContext("您提交的《"+spend.getSummary()+"》报销申请已进入审批环节，最新近况将及时通知您");
			messageDao.newTextMessage(mes);
		}
		return re;
		
		
	}
	@Override
	public int getTodo(String role, String token) {
		int todo=0;
		User user=RedisTool.getInstance().get(token);
		switch(role)
		{
		case "approval":
			todo=financeDao.getTodo(user);
			break;
		}	
		return todo;
	}
}
