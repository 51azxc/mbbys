package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Post;
import com.example.domain.User;
import com.example.mapper.PostMapper;

@Service
@Transactional
public class PostService {

    private PostMapper postMapper;

    public PostService(PostMapper postMapper) {
    	this.postMapper = postMapper;
	}
    
    public int savePost(User user, String body) {
        Post post = new Post();
        post.setBody(body);
        post.setCreateDate(curreateDate());
        post.setUser(user);
        return postMapper.addPost(post);
    }

    public int removePost(int id) {
        return postMapper.deletePost(id);
    }

    public Post getPostById(int id) {
        return postMapper.getPostById(id);
    }

    public List<Post> getUserPost(int userId) {
        return postMapper.getUserPost(userId);
    }

    public List<Post> getAllPost() {
        return postMapper.getAllPosts();
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
