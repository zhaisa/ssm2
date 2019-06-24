package com.jenkins.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jenkins.dao.UserDao;
import com.jenkins.vo.User;
@Transactional
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserDao userRepository;

	@Override
	public int saveIP(Map<String, String> map) {
		String loginIp = map.get("loginIp");
		String username = map.get("username");
		int code = userRepository.updateLoginIpById(loginIp, username);
		return code;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User doLoginCheck(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override

	public User findByUId(int id) {
		return userRepository.findById(id);
	}


	@Override
	public void saveU(User user) {
		userRepository.save(user);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	@Override
	public int updateU(User user) {
		return userRepository.updatePasswordById(user.getPassword(), user.getUsername());
	}

	

	

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		
		return userRepository.findByUsernameAndPassword(username,password);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public int updateLoginIpById(String loginIp, String username) {
		// TODO Auto-generated method stub
		return userRepository.updateLoginIpById(loginIp, username);
	}

	@Override
	public int updatePasswordById(String password, String username) {
		// TODO Auto-generated method stub
		return userRepository.updatePasswordById(password, username);
	}

}
