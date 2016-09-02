package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.Post;
import com.example.domain.User;
import com.example.service.PostService;

@RestController
@SessionAttributes("user")
public class PostController {

    private PostService postService;
    
    public PostController(PostService postService) {
    	this.postService = postService;
	}

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("index")
    public List<Post> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        return posts;
    }

    @PostMapping("p/add")
    public String addPost(@RequestParam("body")String body, @ModelAttribute User user) {
        return postService.savePost(user, body) > 0 ? "success" : "failed";
    }

    @DeleteMapping("p/delete/{id}")
    public String deletePost(@PathVariable int id, @ModelAttribute User user) {
        if (postService.removePost(id) > 0) {
            System.out.println("delete post successful");
        }
        return postService.removePost(id) > 0 ? "success" : "failed";
    }
}
