package com.jenkins.service;

import java.util.Map;

import com.jenkins.vo.User;

public interface IUserService {
	int saveIP(Map<String,String> map);
//	Set<String> getRoles(String username);
//	Set<String> getPermissions(String username);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
	User findById(int id);
	User doLoginCheck(String username,String password);
	User findByUId(int id);
	void saveU(User user);
	int updateU(User user);
	int updateLoginIpById( String loginIp,  String username);
	int updatePasswordById(String password, String username);
}
