package com.example.service;

import com.example.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired PostService postService;
    @Autowired UserService userService;

    @Test
    public void getAllPosts() throws Exception {
        List<Post> posts = postService.getAllPost();
        assertThat(posts.size()).isEqualTo(1);
    }
}
