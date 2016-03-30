package com.test1.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	
	private List<Post> posts = new ArrayList<Post>();
	
	public User() {	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
