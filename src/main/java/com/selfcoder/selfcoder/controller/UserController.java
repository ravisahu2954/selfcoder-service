package com.selfcoder.selfcoder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateUserForm;
import com.selfcoder.selfcoder.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("selfcoder/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<APIResponseDTO> addUser(@Validated @RequestBody CreateUserForm userForm) {

		var apiResponse = userService.addUser(userForm);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<APIResponseDTO> getUserByUserName(@PathVariable String name) {

		var apiResponse = userService.getUserByUserName(name);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}

	@GetMapping
	public ResponseEntity<APIResponseDTO> getUsers(@RequestParam(name = "page",defaultValue = "0") final Integer page,
			@RequestParam(name = "size",defaultValue = "10") final Integer size) {
		log.info("getuser");
		var apiResponse = userService.getUsers(page,size);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDTO> getUserById(@PathVariable("id") final Long id) {

		var apiResponse = userService.getUserById(id);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));

	}

	@DeleteMapping
	public ResponseEntity<APIResponseDTO> deleteUserById(@RequestBody final List<Long> ids) {

		var apiResponse = userService.deleteUserById(ids);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<APIResponseDTO> updateUser(@PathVariable("id") final Long id,@Validated
			@RequestBody CreateUserForm userForm) {

		var apiResponse = userService.updateUser(id, userForm);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}

	@GetMapping("/pas/{pass}")
	public ResponseEntity<APIResponseDTO> deleteUserById(@PathVariable("pass") String pass) {

		var apiResponse = userService.getUserNameAndPassowrd(pass);
		return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getStatus()));
	}
  
}
