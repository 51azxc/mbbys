package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Post;
import com.example.domain.User;
import com.example.service.PostService;
import com.example.service.UserService;

@RestController
public class UserController {

    private UserService userService;
    private PostService postService;
    
    public UserController(UserService userService, PostService postService) {
    	this.userService = userService;
    	this.postService = postService;
	}

    @PostMapping("login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpSession session) {
        User user = userService.login(username, password);
        if (user == null) {
            return "failed";
        } else {
            session.setAttribute("user", user);
            return "success";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "logout";
    }

    @PostMapping("register")
    public String register(@RequestParam("username")String username,
                           @RequestParam("password")String password) {
        if (userService.checkUser(username) > 0) {
            return "username exists";
        }
        User user = new User(username, password);
        return (userService.saveUser(user) > 0) ? "success" : "failed";
    }

    @GetMapping("u/{id}")
    public List<Post> getUserPosts(@PathVariable int id, Model model, HttpSession session) {
        User u = (User)session.getAttribute("user");
        if (u!=null && u.getId() == id) {
            model.addAttribute("me", true);
        }
        List<Post> posts = postService.getUserPost(id);
        return posts;
    }
}
