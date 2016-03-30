package com.test1.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.test1.dao.UserDao;
import com.test1.model.User;

public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	public int saveUser(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		return userDao.addUser(user);
	}

	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}

	public int removeUser(int id) {
		return userDao.deleteUser(id);
	}

	public User login(String username, String password) {
		password = DigestUtils.md5Hex(password);
		return userDao.getUserByUsernameAndPassword(username, password);
	}

	public int checkUser(String username) {
		return userDao.countByUsername(username);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

}
