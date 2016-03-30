package com.test1.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test1.dao.PostDao;
import com.test1.model.Post;
import com.test1.model.User;

public class PostService implements IPostService {

	@Autowired
	private PostDao postDao;
	
	public int savePost(User user, String body) {
		Post post = new Post();
		post.setBody(body);
		post.setCreateDate(curreateDate());
		post.setUser(user);
		return postDao.addPost(post);
	}

	public int removePost(int id) {
		return postDao.deletePost(id);
	}

	public Post getPostById(int id) {
		return postDao.getPostById(id);
	}

	public List<Post> getUserPost(int userId) {
		return postDao.getUserPost(userId);
	}

	public List<Post> getAllPost() {
		return postDao.getAllPosts();
	}

	private String curreateDate() {
		String date = "";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			date = sdf.format(cal.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
}
