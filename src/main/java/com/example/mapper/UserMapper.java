package com.example.mapper;

import com.example.domain.Post;
import com.example.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(int id);
    List<Post> getPosts(int id);
    int countByUsername(@Param("username")String username);
    User getUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
