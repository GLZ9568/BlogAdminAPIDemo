package com.cg.demo.service;

import com.cg.demo.model.Post;
import com.cg.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.demo.util.ValFactory;

@Service
public class AdminService {
    /* this is the service class contains the business logic and data processing functions */

    // logger for the AdminService class for debugging purpose
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    // retrieve the user data from the USERS_API_URL
    public List<User> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(ValFactory.USERS_API_URL, User[].class);
        return Arrays.asList(users);
    }

    // retrieve the post data from the POSTS_API_URL
    public List<Post> getAllPosts() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject(ValFactory.POSTS_API_URL, Post[].class);
        return Arrays.asList(posts);
    }

    // match the post data to each corresponding user data
    public List<User> getUsersWithPosts() {
        List<User> users = getAllUsers();
        List<Post> posts = getAllPosts();

        for (User user : users) {
            List<Post> userPosts = posts.stream()
                    .filter(post -> post.getUserId() == user.getId())
                    .collect(Collectors.toList());
            user.setPosts(userPosts);
        }
        return users;
    }

    // get all the posts for a given userId
    public List<Post> getPostsByUserId(int userId) {
        List<Post> allPosts = getAllPosts();
        List<Post> userPosts = allPosts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
        return userPosts;
    }
}
