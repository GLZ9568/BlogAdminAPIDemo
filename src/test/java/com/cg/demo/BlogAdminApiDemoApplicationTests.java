package com.cg.demo;

import com.cg.demo.model.Post;
import com.cg.demo.model.User;
import com.cg.demo.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BlogAdminApiDemoApplicationTests {
	/* this is the test class that uses Junit and Mockito to run tests on the application */

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AdminService adminService;

	@Test
	public void testGetAllUsers() throws Exception {
		// test retrieving all users data
		List<User> users = adminService.getAllUsers();
		// check if the original users size is 10
		assertEquals(10, users.size());
		// send HTTP GET request to /admin/users API endpoint, get result in JSON format
		mockMvc.perform(get("/admin/users")
						.contentType(MediaType.APPLICATION_JSON))
				// check if HTTP status is ok
				.andExpect(status().isOk())
				// check if the retrieved response from the /admin/users API matches the size of the original users data, which is 10
				.andExpect(jsonPath("$", hasSize(users.size())));
	}

	@Test
	public void testGetAllPosts() throws Exception {
		// test retrieving all posts data
		List<Post> posts = adminService.getAllPosts();
		// check if the original posts size is 100
		assertEquals(100, posts.size());
		// send HTTP GET request to /admin/posts API endpoint, get result in JSON format
		mockMvc.perform(get("/admin/posts")
						.contentType(MediaType.APPLICATION_JSON))
				// check if HTTP status is ok
				.andExpect(status().isOk())
				// check if the retrieved response from the /admin/posts API matches the size of the original posts data, which is 100
				.andExpect(jsonPath("$", hasSize(posts.size())));
	}

	@Test
	public void testGetUsersWithPosts() throws Exception {
		// test retrieving users with posts data
		List<User> usersWithPosts = adminService.getUsersWithPosts();
		List<User> users = adminService.getAllUsers();
		List<Post> posts = adminService.getAllPosts();
		// send HTTP GET request to /admin/users-with-posts API endpoint, get result in JSON format
		mockMvc.perform(get("/admin/users-with-posts")
						.contentType(MediaType.APPLICATION_JSON))
				// check if HTTP status is ok
				.andExpect(status().isOk())
				// check if the 3rd user's id from the /admin/users-with-posts API matches the user id of the 3rd user in the original users data
				.andExpect(jsonPath("$[2].id").value(users.get(2).getId()))
				// check if the 3rd user's 6th post's id matches the 26th post's id from the original post data, which should be:
				/*
					{
            			"userId":3,
            			"id":25,
            			"title":"rem alias distinctio quo quis",
            			"body":"ullam consequatur ut\nomnis quis sit vel consequuntur\nipsa eligendi ipsum molestiae et omnis error nostrum\nmolestiae illo tempore quia et distinctio"
         			}
				 */
				.andExpect(jsonPath("$[2].posts[5].id").value(posts.get(25).getId()));
	}
}
