package com.selfcoder.selfcoder.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.BodySpec;
import org.springframework.test.web.reactive.server.WebTestClient.RequestBodySpec;
import org.springframework.test.web.reactive.server.WebTestClient.RequestHeadersSpec;

import com.selfcoder.selfcoder.constant.UserConstant;
import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateUserForm;
import com.selfcoder.selfcoder.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private WebTestClient webTestClient;

	private CreateUserForm createUserForm;
	

	@BeforeEach
	void init() {
		webTestClient = WebTestClient.bindToController(userController).build();
		createUserForm = new CreateUserForm();
	    createUserForm.setEmail("ravisahu2954@gmail.com");
	    createUserForm.setUserName("ravisahu2954@gmail");
	    createUserForm.setPassword("python123");
	}

	
	
	
	
    @ParameterizedTest(name = "userName={0}")
	@DisplayName("Test user creation with invalid user names")
	@NullAndEmptySource
	@ValueSource(strings = {" ", "   "})
	void testCreateUser_withInvalidUserNames(String userName) {
	    createUserForm.setUserName(userName);
	    webTestClient.post()
	            .uri("/selfcoder/v1/users")
	            .bodyValue(createUserForm)
	            .exchange()
	            .expectStatus()
	            .isEqualTo(HttpStatus.BAD_REQUEST);
	}
	@Test
	@DisplayName("This test case cover the scenario when email is missing ")
	void testCreateUser_when_email_is_Missing() {
	
         createUserForm.setEmail(null);
		 webTestClient.post().uri("/selfcoder/v1/users").bodyValue(createUserForm).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
	
	@Test
	@DisplayName("This test case cover the scenario when userName is missing ")
	void testCreateUser_when_userName_is_Missing() {
	
         createUserForm.setUserName(null);
		 webTestClient.post().uri("/selfcoder/v1/users").bodyValue(createUserForm).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
	
	
	
	@Test
	@DisplayName("This test case cover the scenario for getUserById")
	void testGetUserById_Success() {
	
		when(userService.getUserById(1L)).thenReturn(APIResponseDTO.builder()
				.message(UserConstant.GET_USER_BY_ID)
				.status(HttpStatus.OK.value())
				.build());
		 webTestClient.get().uri("/selfcoder/v1/users/1").exchange().expectStatus().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("This test case covers the scenario when a user Not Found by id")
	void testUserNotFoundExceptionById()
	{
	  
       when(userService.getUserById(1L)).thenReturn(APIResponseDTO.builder()
			    .message(UserConstant.NO_CONTENT_FOUND)
				.timeStamp(System.currentTimeMillis())
				.status(HttpStatus.NO_CONTENT.value())
				.build());
	   webTestClient.get().uri("/selfcoder/v1/users/1").exchange().expectStatus().isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	@DisplayName("This test case cover the scenario for getUserDetails")
	void testGetUserDetails_Success() {
	
		when(userService.getUsers(0,10)).thenReturn(APIResponseDTO.builder()
				.message(UserConstant.ALL_USER_FETCHED)
				.status(HttpStatus.OK.value())
				.build());
		 webTestClient.get().uri("/selfcoder/v1/users").exchange().expectStatus().isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("This test case covers the scenario when a user Not Found")
	void testUserNotFoundException()
	{
	  
       when(userService.getUsers(0,10)).thenReturn(APIResponseDTO.builder()
			    .message(UserConstant.NO_CONTENT_FOUND)
				.timeStamp(System.currentTimeMillis())
				.status(HttpStatus.NO_CONTENT.value())
				.build());
	   webTestClient.get().uri("/selfcoder/v1/users").exchange().expectStatus().isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	@ParameterizedTest(name = "userName={0}")
    @NullAndEmptySource
    @ValueSource(strings = {" ","  "})
	@DisplayName("This test case cover scenarios when userName is empty, blank, or null in UpdateUserForm")
	void testUpdateUser_when_userName_is_missing_or_blank_or_null(String userName) {
	    createUserForm.setUserName(userName);
	    webTestClient.put().uri("/selfcoder/v1/users/1").bodyValue(createUserForm)
	            .exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
	}
    
	
	
	
	@Test
	@DisplayName("This test case cover the scenario when userName is null in UpdateCampaingForm")
	void testUpdateUser_when_userName_is_null() {
	
		 createUserForm.setUserName(null);
		 webTestClient.put().uri("/selfcoder/v1/users/1").bodyValue(createUserForm).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

	
	
}
