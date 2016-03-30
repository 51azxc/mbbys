package com.test1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test1.model.Post;
import com.test1.model.User;
import com.test1.service.IPostService;
import com.test1.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IPostService postService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username")String username, 
			@RequestParam("password")String password,
			Model model, HttpSession session) {
		User user = userService.login(username, password);
		if (user == null) {
			model.addAttribute("message","用户名或密码无效");
			return "login";
		} else {
			session.setAttribute("user", user);
			return "redirect:/u/"+user.getId();
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:login";
	}
	
	@RequestMapping("/checkUsername")
	@ResponseBody
	public int checnUsername(@RequestParam("username")String username) {
		return userService.checkUser(username);
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("username")String username, 
			@RequestParam("password")String password, 
			Model model) {
	  if (userService.checkUser(username) > 0) {
	    model.addAttribute("message", "用户名已存在");
      return "register";
	  }
		User user = new User(username, password);
		if (userService.saveUser(user) > 0) {
			return "redirect:login";
		} else {
			model.addAttribute("message", "something went wrong");
			return "register";
		}
	}
	
	@RequestMapping("/u/{id}")
	public String getUserPage(@PathVariable int id, Model model, HttpSession session) {
	  User u = (User)session.getAttribute("user");
	  if (u!=null && u.getId() == id) {
	    model.addAttribute("me", true);
	  }
		List<Post> posts = postService.getUserPost(id);
		model.addAttribute("posts", posts);
		return "index";
	}
}
