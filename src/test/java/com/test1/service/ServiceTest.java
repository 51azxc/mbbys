package com.test1.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.test1.config.DataConfig;
import com.test1.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfig.class, ServiceTestConfig.class})
@Transactional
public class ServiceTest {
  
	@Autowired
	private IUserService userService;
	@Autowired
	private IPostService postService;
	private Gson gson;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
		gson = new Gson();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
	
	@Ignore
	@Test
	@Commit
	public void testSaveUser() throws Exception {
		User u = new User("1","1");
		System.out.println(userService.saveUser(u));
	}
	
	@Test
	public void testGetUser() throws Exception {
		System.out.println(gson.toJson(userService.login("1", "1")));
	}
	
	@Test
	@Rollback
	public void testRemoveUser() throws Exception {
		Assert.assertEquals("delete User", userService.removeUser(1), 1);
	}
	
	@Test
	@Commit
	public void testSavePost() throws Exception {
		User u = userService.login("1", "1");
		String body = "aaaaaaaaa";
		Assert.assertEquals(postService.savePost(u, body), 1);
	}
	
	@Test
	public void testUserPosts() throws Exception {
		System.out.println(gson.toJson(postService.getUserPost(1)));
	}
	
	@Test
	public void testUserGetPosts() throws Exception {
		System.out.println(gson.toJson(userService.getUserById(1)));
	}
}

@Configuration
class ServiceTestConfig {
  
  @Bean
  public IUserService userService() {
    return new UserService();
  }
  
  @Bean
  public IPostService postService() {
    return new PostService();
  }
}
