package cn.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.services.SecurityService;


public class BaseFilter implements HandlerInterceptor {
	

    protected String roleid;
    BaseFilter(String roleid)
    {
    	this.roleid=roleid;
    }
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Autowired
	 SecurityService securityService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String token="";
		try{
			javax.servlet.http.Cookie[] cs=request.getCookies();
			for(javax.servlet.http.Cookie cookie : cs)
			{
				if(cookie.getName().equals("dptoken"))
				{
					token=cookie.getValue();
				}
			}
		}catch(Exception e)
		{
			return false;
		}
		boolean exist=securityService.hasRole(token,roleid);
		return exist;
	}
}