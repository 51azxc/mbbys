package com.test1.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.test1.config.WebConfig;
import com.test1.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
@Transactional
public class ControllerTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;
  
  @Before
  public void setup(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }
  
  @Test
  public void testGetUserPosts() throws Exception {
    User u = new User(1,"1");
    mockMvc.perform(get("/u/{id}",u.getId()).sessionAttr("user", u))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists("posts"))
           .andReturn();
  }
  
  @Test
  public void testLogin() throws Exception {
    mockMvc.perform(post("/login").param("username", "1").param("password", "1"))
           .andExpect(status().isFound())
           .andExpect(redirectedUrl("/u/1"))
           .andDo(print())
           .andReturn();
  }
  
  @Test
  public void test404NF() throws Exception {
    mockMvc.perform(get("/ddddd"))
           .andExpect(status().isNotFound())
           .andReturn();
  }
  
  @Test
  public void testExistsName() throws Exception {
    mockMvc.perform(get("/checkUsername","1").param("username", "1").contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(status().isOk())
           .andDo(print())
           .andReturn();
  }

}
