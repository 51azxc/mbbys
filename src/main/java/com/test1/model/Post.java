package com.test1.model;

public class Post {

	private int id;
	private String body;
	private String createDate;
	
	private User user;
	
	public Post() {	}

	public int getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
