package com.test1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test1.model.Post;
import com.test1.model.User;

public interface UserDao {
	
	@Results(id = "userResult", value = {
			@Result(property = "id", column = "id", id = true),
			@Result(property = "username", column = "username"),
			@Result(property = "posts", column = "id", javaType = List.class, many = @Many(select="getPosts"))
	})
	@Select("select * from user where id = #{id}")
	public User getUserById(int id);
	
	@Select("select post.id, post.body, post.createDate from post where post.user_id = #{id}")
	public List<Post> getPosts(int id);
	
	@Select("select count(1) from user where username = #{username}")
	public int countByUsername(@Param("username")String username);
	
	@Results(id = "user", value = {
			@Result(property = "id", column = "id", id = true),
			@Result(property = "username", column = "username")
	})
	@Select("select id, username from user where username = #{username} and password = #{password}")
	public User getUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	
	@Insert("insert into user (username, password) values(#{username}, #{password})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public int addUser(User user);
	
	@Update("update user set username = #{username}, password = #{password} where id = #{id}")
	public int updateUser(User user);
	
	@Delete("delete from user where id = #{id}")
	public int deleteUser(int id);
}
