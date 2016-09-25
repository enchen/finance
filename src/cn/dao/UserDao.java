package cn.dao;

import java.util.List;

import cn.beans.User;

public interface UserDao {
	void initWXUser(List<User> users);
	User initRole(String userid);
	List<User> getUsers(String role);
	

}
