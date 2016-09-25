package cn.services;

import java.util.List;

import cn.beans.Role;
import cn.beans.User;

public interface UserService {
void initUsers();//从企业号用户列表初始化本地用户表	
String checkCode(String code);
String test(String userid);
List<User> getUsers(String role);
}
