package cn.services.impl;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.beans.Role;
import cn.beans.User;
import cn.dao.UserDao;
import cn.services.UserService;
import cn.util.Constants;
import cn.util.HttpUtils;
import cn.util.RedisTool;
import cn.util.Tools;
import cn.dto.WXUsers;
@Service
public class UserServiceImpl implements UserService {
	private Logger log=Logger.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Override
	public void initUsers() {
		String respStr=HttpUtils.httpsGet(Constants.GetDepartmentUsersUrl);
		if(!"".equals(respStr)){
		
			try{
				 ObjectMapper mapper = new ObjectMapper();
				 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//未对应字段忽略
				  WXUsers wxUser= (WXUsers)mapper.readValue(respStr, WXUsers.class);
				  if(wxUser!=null&&wxUser.getUserlist().size()>0)
				  {
					  userDao.initWXUser(wxUser.getUserlist());
				  }
				 
			} catch (Exception e) {
				log.equals("获取用户列表失败");
				System.out.print(e.getMessage());
			}
		}
		
	}
	@Override
	public String checkCode(String code) {
		String respStr=HttpUtils.httpsGet(Constants.GetIdByCode+code);
		String userId="";
		String newToken="";
		if(!"".equals(respStr))
		{
			 ObjectMapper mapper = new ObjectMapper(); 
				try{
					JsonNode rootNode = mapper.readTree(respStr); 
					userId= rootNode.get("UserId").asText(); 
				} catch (Exception e) {
					newToken="";
				}
				if(!"".equals(userId))
				{
					User user=userDao.initRole(userId);
					if(user!=null)
					{
						newToken=Tools.generateToken();
						RedisTool.getInstance().put(newToken, user);
					}
				}
		}
		// TODO Auto-generated method stub
		return newToken;
	}
	@Override
	public String test(String userid) {
		// TODO Auto-generated method stub
		String newToken="";
		User user=userDao.initRole(userid);
		if(user!=null)
		{
			newToken=Tools.generateToken();
			RedisTool.getInstance().put(newToken, user);
		}
		return newToken;
	}
	@Override
	public List<User> getUsers(String role) {
		// TODO Auto-generated method stub
		return userDao.getUsers(role);
	}

}
