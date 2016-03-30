package com.test1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test1.model.Post;
import com.test1.model.User;
import com.test1.service.IPostService;

@Controller
@SessionAttributes("user")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@RequestMapping(value={"/", "/index"})
  public String index(Model model) {
    List<Post> posts = postService.getAllPost();
    model.addAttribute("posts", posts);
    return "index";
  }
	
	@RequestMapping(value = "/p/add", method = RequestMethod.POST)
	public String addPost(@RequestParam("body")String body, @ModelAttribute User user) {
	  if (postService.savePost(user, body) > 0) {
	    System.out.println("add post successful");
	  }
		return "redirect:/u/"+user.getId();
	}
	
	@RequestMapping(value = "/p/delete/{id}", method = RequestMethod.POST)
	public String deletePost(@PathVariable int id, @ModelAttribute User user) {
	  if (postService.removePost(id) > 0) {
      System.out.println("delete post successful");
    }
		return "redirect:/u/"+user.getId();
	}
	
}
