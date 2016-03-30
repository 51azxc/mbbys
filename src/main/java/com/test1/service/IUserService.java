package com.test1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test1.model.User;

@Service
public interface IUserService {
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveUser(User user);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int modifyUser(User user);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int removeUser(int id);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User login(String username, String password);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int checkUser(String username);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User getUserById(int id);
}
