# Blog Admin API Demo

This Java-based web application demonstrates how to consume and display user and post data from an existing Blog API. The project uses the [Spring Boot](https://spring.io/projects/spring-boot) framework for web development and testing.

## Web Framework

- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot)
- **Build tool**: [Maven](https://maven.apache.org/)
- **Testing**: [JUnit](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/)

## Implemented Admin APIs

The application provides the following Admin API endpoints:

1. `GET /admin/users`: Retrieves all users data
2. `GET /admin/posts`: Retrieves all posts data
2. `GET /admin/users-with-posts`: Retrieves all users and their related posts
2. `GET /admin/users/{userId}/posts`: Retrieves all related posts per userId
5. a hiden API which is not listed here

## Test Cases

I have implemented the following 3 test cases:

1. `testGetAllUsers()`: test retrieving all users data, and compare the size of the data retrieved from the `GET /admin/users` API against the original users data
2. `testGetAllPosts()`: test retrieving all posts data, and compare the size of the data retrieved from the `GET /admin/posts` API against the original posts data
3. `testGetUsersWithPosts()`: test retrieving users with posts data, and check if the data retrieved from the `GET /admin/users-with-posts` API is the data expected against the original users and posts data

## How to Run the Application

I have only tested running the application from the IntelliJ IDE so far, so to run the application:

1. Clone the repository from https://github.com/GLZ9568/BlogAdminAPIDemo.git
2. Load the project into IntelliJ
3. In IntelliJ IDE use Maven to load all the dependencies
4. Run the Spring Boot application entry class `BlogAdminApiDemoApplication.java` from IntelliJ and wait for the Spring Boot Tomcat server to complete start
5. Open broswer and nevigate to `http://localhost:8080/admin/<api>` for each of the API described in the API section of this README file
6. For test cases, run the `BlogAdminApiDemoApplicationTests` from the IntelliJ IDE

## Sample Test Outputs

I have put a the following test outputs from my tests in this repositry as well:

- **API /admin/users**: [UsersOutput](https://github.com/GLZ9568/BlogAdminAPIDemo/blob/master/src/main/resources/UsersOutput)
- **API /admin/posts**: [PostsOutput](https://github.com/GLZ9568/BlogAdminAPIDemo/blob/master/src/main/resources/PostsOutput)
- **API /admin/users-with-posts**: [UsersWithPostsOutput](https://github.com/GLZ9568/BlogAdminAPIDemo/blob/master/src/main/resources/UsersWithPostsOutput)
- **API /admin/users/{userId}/posts**: [UsersUIDPostsOutput](https://github.com/GLZ9568/BlogAdminAPIDemo/blob/master/src/main/resources/UsersUIDPostsOutput)
