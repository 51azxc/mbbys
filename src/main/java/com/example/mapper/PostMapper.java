package com.example.mapper;

import com.example.domain.Post;
import com.example.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    Post getPostById(int id);

    User getUser(int id);

    List<Post> getUserPost(int userId);

    List<Post> getAllPosts();

    int addPost(Post post);

    int deletePost(int id);
}
