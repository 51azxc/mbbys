package com.test1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test1.model.Post;
import com.test1.model.User;

@Service
public interface IPostService {

	@Transactional(propagation=Propagation.REQUIRED)
	public int savePost(User user, String body);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int removePost(int id);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Post getPostById(int id);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Post> getUserPost(int userId);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Post> getAllPost();
	
}
