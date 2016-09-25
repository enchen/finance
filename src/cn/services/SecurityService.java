package cn.services;

public interface SecurityService {
	boolean hasRole(String token,String roleid);

}
