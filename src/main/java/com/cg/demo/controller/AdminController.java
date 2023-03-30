package com.cg.demo.controller;

import com.cg.demo.model.Post;
import com.cg.demo.model.User;
import com.cg.demo.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /* this is the controller class handles incoming HTTP requests from clients */

    // logger for the AdminController class for debugging purpose
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    // root API only for me ...
    @GetMapping("/")
    public String home(){
        return "Welcome Master Chunsta!<br><br>      O<br>     /|\\<br>     / \\<br>";
    }

    // API /admin/users retrieves all original users data
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // API /admin/posts retrieves all original posts data
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = adminService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // API /admin/users-with-posts retrieves all users and their related posts
    @GetMapping("/users-with-posts")
    public ResponseEntity<List<User>> getUsersWithPosts() {
        List<User> usersWithPosts = adminService.getUsersWithPosts();
        return new ResponseEntity<>(usersWithPosts, HttpStatus.OK);
    }

    // API /admin//users/{userId}/posts retrieves all related posts per userId
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable("userId") int userId) {
        List<Post> posts = adminService.getPostsByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
