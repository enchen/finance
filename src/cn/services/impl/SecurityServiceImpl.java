package cn.services.impl;


import org.springframework.stereotype.Service;

import cn.beans.Role;
import cn.beans.User;
import cn.services.SecurityService;
import cn.util.RedisTool;
@Service
public class SecurityServiceImpl implements SecurityService {
	@Override
	public boolean hasRole(String token, String roleid) {
		User user=RedisTool.getInstance().get(token);
		if(user==null)
		{
			return false;
		}
		for(Role role : user.getRoles())
		{
			if(role.getRoleId().equals(roleid))
			{
				return true;
			}
		}
		return false;
	}

}
