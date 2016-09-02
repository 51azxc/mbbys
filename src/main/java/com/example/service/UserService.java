package com.example.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@Service
@Transactional
public class UserService {

    private UserMapper userMapper;
    
    public UserService(UserMapper userMapper) {
    	this.userMapper = userMapper;
	}

    public int saveUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userMapper.addUser(user);
    }

    public int modifyUser(User user) {
        return userMapper.updateUser(user);
    }

    public int removeUser(int id) {
        return userMapper.deleteUser(id);
    }

    public User login(String username, String password) {
        password = DigestUtils.md5Hex(password);
        return userMapper.getUserByUsernameAndPassword(username, password);
    }

    public int checkUser(String username) {
        return userMapper.countByUsername(username);
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
