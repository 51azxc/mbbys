package com.test1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.test1.model.Post;
import com.test1.model.User;

public interface PostDao {

	@Results(id = "postResult", value = {
			@Result(property = "id", column = "id", id = true),
			@Result(property = "body", column = "body"),
			@Result(property = "createDate", column = "createDate"),
			@Result(property = "user", column = "user_id", javaType = User.class, one = @One(select = "getUser")),
	})
	@Select("select * from post where id = #{id}")
	public Post getPostById(int id);
	
	@Select("select id, username from user where id = #{id}")
	public User getUser(int id);
	
	@ResultMap("postResult")
	@Select("select * from post where user_id = #{userId} order by createDate desc")
	public List<Post> getUserPost(int userId);
	
	@ResultMap("postResult")
	@Select("select * from post order by createDate desc")
	public List<Post> getAllPosts();
	
	@Insert("insert into post (body, createDate, user_id) values(#{body}, #{createDate}, #{user.id})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public int addPost(Post post);
	
	@Delete("delete from post where id = #{id}")
	public int deletePost(int id);
}
