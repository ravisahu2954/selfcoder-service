package com.selfcoder.selfcoder.service;

import java.util.List;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateUserForm;

public interface UserService {

	APIResponseDTO addUser(CreateUserForm createUserForm);

	APIResponseDTO getUsers(Integer page,Integer size);

	APIResponseDTO getUserByUserName(String name);

	APIResponseDTO getUserById(Long id);

	APIResponseDTO deleteUserById(List<Long> id);

	APIResponseDTO updateUser(Long id, CreateUserForm createUserForm);

	APIResponseDTO getUserNameAndPassowrd(String pass);
	
}
