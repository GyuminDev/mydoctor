package com.mydoctor.service;

import com.mydoctor.dao.UserDao;
import com.mydoctor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	private UserDao userDao;

	public User getUserById(String id, String password) {
		return userDao.getUserById(id, password);
	}

	

	public void setIsLogin(User user) {
		this.userDao.setIsLogin(user);
		
	}

}
