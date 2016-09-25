package cn.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.beans.Project;
import cn.beans.Role;
import cn.beans.Spend;
import cn.beans.User;
import cn.services.FinanceService;
import cn.services.ProjectService;
import cn.services.UserService;
import cn.util.RedisTool;
import cn.util.Tools;

@RestController
public class FinanceController {
	 @Autowired
	 FinanceService financeService;
	 @Autowired
	 ProjectService projectService;
	 @Autowired
	 UserService userService;
	 @RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody List<Role> test(HttpServletResponse response)
	 {
		String token= userService.test("chenen");
		 Cookie cookie = new Cookie("dptoken", token);  
	         cookie.setPath("/");  
			 response.addCookie(cookie);
			 return RedisTool.getInstance().get(token).getRoles();	
	 }
	 @RequestMapping(value = "/initusers", method = RequestMethod.GET)
	public void initUser()
	 {
		userService.initUsers();
	 }
	 
	 @RequestMapping(value = "/user/getusers", method = RequestMethod.GET)
		public List<User> getUsers(@RequestParam String role)//get不能传body此处传递params
		 {
			return userService.getUsers(role);
		 }
	 
	 @RequestMapping(value = "/user/gettodo", method = RequestMethod.GET)
		public int getTodo(@RequestParam String role,@CookieValue String dptoken)//获取当前模块待处理任务数
		 {
			return financeService.getTodo(role, dptoken);
		 }
	 @RequestMapping(value = "/user/newspend", method = RequestMethod.POST)
		public @ResponseBody int newSpend(@RequestBody Spend spend,@CookieValue String dptoken)
		 {
		 return financeService.createNewSpend(spend,dptoken);
		 }
	 
	 @RequestMapping(value = "/user/getavailableprojects", method = RequestMethod.GET)
		public @ResponseBody List<Project> getAvailableProjects()
		 {
		 List<Project> ps= projectService.getAvailableProjects();
		 return ps;
		 }
	 
	 @RequestMapping(value="/checkcode",method=RequestMethod.GET,produces = "text/plain; charset=utf-8")//
	 public @ResponseBody List<Role> createProject(@RequestBody String code,HttpServletResponse response)
	 {
		 List<Role> roles=null;
		 String newToken=userService.checkCode(code);
		 if(newToken!=null&&!newToken.endsWith(""))
		 {
		 roles=RedisTool.getInstance().get(newToken).getRoles();
		 Cookie cookie = new Cookie("dptoken", newToken);  
        // cookie.setMaxAge(60 * 60);// 设置为60min  不设置只在本次会话有效
         cookie.setPath("/");  
		 response.addCookie(cookie);
		 }
		 return roles;
	 }
	 

}
