package com.selfcoder.selfcoder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.selfcoder.selfcoder.entity.User;
import com.selfcoder.selfcoder.exception.ProblemAlreadyExistException;
import com.selfcoder.selfcoder.exception.UserNotFoundException;
import com.selfcoder.selfcoder.form.CreateUserForm;
import com.selfcoder.selfcoder.repository.UserRepository;
import com.selfcoder.selfcoder.serviceImpl.UserServiceImpl;

/**
 * This is the {@link UserService} test cases class. It will cover the test cases for the user services.
 * @author Ravi Sahu
 */

@SpringBootTest
public class UserServiceImplTest {

	//first way to do unit testing
	private UserServiceImpl userServiceImpl; // test object
	
	private UserRepository userRepository; //mock object
	
	private ModelMapper modelMapper; // mock object
	
	private  PasswordEncoder passwordEncoder; // mock object

	 CreateUserForm createUserForm;
	
	@BeforeEach
	void setUp()
	{
		userRepository = mock(UserRepository.class);
		modelMapper = mock(ModelMapper.class);
		passwordEncoder = mock(PasswordEncoder.class);
		userServiceImpl = new UserServiceImpl(modelMapper, userRepository);
		
	}
	
	@BeforeEach
	void init() {
	
	  createUserForm = new CreateUserForm();
	  createUserForm.setEmail("ravisahu2954@gmail.com");
	  createUserForm.setUserName("ravi9164");
	  createUserForm.setPassword("python@123");
	
	}
	
	@Test
	@DisplayName("add user success test")
	void addUser_SuccessTest()
	{
	
		when(userRepository.findByUserName(createUserForm.getUserName())).thenReturn(Optional.empty());
		assertEquals(HttpStatus.CREATED.value(), userServiceImpl.addUser(createUserForm).getStatus());
		verify(userRepository,times(1)).findByUserName(createUserForm.getUserName());
		
	}
	
	@Test
	@DisplayName("when user already exist case")
	void addUser_ExceptionTest()
	{
	
		when(userRepository.findByUserName(createUserForm.getUserName())).thenReturn(Optional.of(new User()));
        assertThrows(ProblemAlreadyExistException.class, ()->userServiceImpl.addUser(createUserForm));
		verify(userRepository,times(1)).findByUserName(createUserForm.getUserName());
		
	}
	
	@Test
	@DisplayName("get users success test")
	void getUsers_SuccessTest()
	{
        List<User> users = new ArrayList<>();
        users.add(new User());
		Page<User> emptyUserPage = new PageImpl<>(users);
	    Sort sort = Sort.by(Sort.Direction.DESC,"updatedAt");
        when(userRepository.findAll(PageRequest.of(0, 10,sort))).thenReturn(emptyUserPage);
	    assertEquals(HttpStatus.OK.value(),userServiceImpl.getUsers(0,10).getStatus());
        
	}
	
	@Test
	@DisplayName("when, there is no user")
	void getUsers_ExceptionTest()
	{
        Page<User> emptyUserPage = new PageImpl<>(new ArrayList<>());
	    Sort sort = Sort.by(Sort.Direction.DESC,"updatedAt");
        when(userRepository.findAll(PageRequest.of(0, 10,sort))).thenReturn(emptyUserPage);
	    assertThrows(UserNotFoundException.class,()-> userServiceImpl.getUsers(0,10));
        
	}
	
	@Test
	@DisplayName("get user by username success test")
	void getUserByUserName_SuccessTest()
	{
   
        when(userRepository.findByUserName("ravi")).thenReturn(Optional.of(new User()));
	    assertEquals(HttpStatus.OK.value(),userServiceImpl.getUserByUserName("ravi").getStatus());
        
	}
	
	@Test
	@DisplayName("get user by username exception test")
	void getUserByUserName_ExceptionTest()
	{
   
        when(userRepository.findByUserName("ravi")).thenReturn(Optional.empty());
	    assertThrows(UserNotFoundException.class,()->userServiceImpl.getUserByUserName("ravi"));
        
	}
	
	@Test
	@DisplayName("get user by user id success test")
	void getUserById_SuccessTest()
	{
   
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
	    assertEquals(HttpStatus.OK.value(),userServiceImpl.getUserById(1L).getStatus());
        
	}
	
	@Test
	@DisplayName("get user by id exception test")
	void getUserById_ExceptionTest()
	{
   
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
	    assertThrows(UserNotFoundException.class,()->userServiceImpl.getUserById(1L).getStatus());
        
	}
    
	@Test
	@DisplayName("delete user by id success test")
	void deleteUserById_SuccessTest()
	{
	     List<Long> ids = new ArrayList();
	     ids.add(1L);
	     doNothing().when(userRepository).deleteAllById(ids);
	     assertEquals(HttpStatus.OK.value(), userServiceImpl.deleteUserById(ids).getStatus());
		 verify(userRepository,times(1)).deleteAllById(ids);
	}
	
}
