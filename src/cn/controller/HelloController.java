package cn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.beans.User;
import cn.dao.UserDao;

@RestController
public class HelloController {

	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public String listAllUsers() {
//	    User user = new User();
//	    user.setName("wdy");
//	    user.setPsw("znn");
	    return "你大爷";
	    }


	
	 @RequestMapping(value="/getUserName",method=RequestMethod.POST)
	    public String getUserName(@RequestParam(value="name") String name){
	        return name;
	    }

	 
	 @Autowired
	 private  UserDao td;
	 //@Autowired
	

	 @RequestMapping("getFavUser")
	    public User getFavUser(@RequestParam("userName") String userName,String userId,int userAge){
	        User favUser = new User();
	       // favUser.setName("wdy");
	       // favUser.setPsw("zmm");;
	        
	        return favUser;
	    }
}
