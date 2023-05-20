package com.selfcoder.selfcoder.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.selfcoder.selfcoder.constant.UserConstant;
import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.dto.UserDTO;
import com.selfcoder.selfcoder.entity.Blog;
import com.selfcoder.selfcoder.entity.Problem;
import com.selfcoder.selfcoder.entity.User;
import com.selfcoder.selfcoder.entity.Course;

import com.selfcoder.selfcoder.exception.CustomBadRequestException;
import com.selfcoder.selfcoder.exception.ProblemAlreadyExistException;
import com.selfcoder.selfcoder.exception.UserNotFoundException;
import com.selfcoder.selfcoder.form.CreateUserForm;
import com.selfcoder.selfcoder.repository.UserRepository;
import com.selfcoder.selfcoder.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public APIResponseDTO addUser(CreateUserForm createUserForm) {
		log.info("addUser");

		Optional<User> opt = userRepository.findByUserName(createUserForm.getUserName());
		if (opt.isPresent()) {
			throw new ProblemAlreadyExistException(UserConstant.USER_ALREADY_EXISTS);
		}
 
		createUserForm.setPassword(passwordEncoder.encode(createUserForm.getPassword()));
		User user = modelMapper.map(createUserForm, User.class);
		userRepository.save(user);
		log.debug("User is added"); 
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.CREATED.value()).build();
	}

	@Override
	public APIResponseDTO getUsers(Integer page,Integer size) {
	    Sort sort = Sort.by(Sort.Direction.DESC,"updatedAt");
		Page<User> listOfUser = userRepository.findAll(PageRequest.of(page,size,sort));
		List<UserDTO> listDTO = null;
		if (listOfUser.isEmpty())
			throw new UserNotFoundException("Data not found");
		else {
			listDTO = listOfUser.stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
		}
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).data(listDTO).message("data found")
				.success(true).status(HttpStatus.OK.value()).build();
	}
   
	@Override
	public APIResponseDTO getUserByUserName(String name) {
		Optional<User> b = userRepository.findByUserName(name);
		if (b.isPresent()) {
			return APIResponseDTO.builder().timeStamp(System.currentTimeMillis())
					.data(modelMapper.map(b.get(), UserDTO.class)).message("data found").success(true)
					.status(HttpStatus.OK.value()).build();
		} else
			throw new UserNotFoundException("Data not found");
	}

	@Override
	public APIResponseDTO getUserById(Long id) {
		Optional<User> b = userRepository.findById(id);
		if (b.isPresent()) {
			return APIResponseDTO.builder().timeStamp(System.currentTimeMillis())
					.data(modelMapper.map(b.get(), UserDTO.class)).message("data found").success(true)
					.status(HttpStatus.OK.value()).build();
		} else
			throw new UserNotFoundException("Data not found");
	}

	@Override
	public APIResponseDTO deleteUserById(List<Long> ids) {
		log.info("## Deleting User ## {}", ids);
		if (ids == null) {
			throw new CustomBadRequestException("ids cannot be null!");
		}
		try {
			userRepository.deleteAllById(ids);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("data not found");
		}
		log.info("## User deleted ##");
		return APIResponseDTO.builder().message("User deleted").timeStamp(System.currentTimeMillis()).success(true)
				.status(HttpStatus.OK.value()).build();
	}

	@Override
	public APIResponseDTO updateUser(Long id, CreateUserForm createUserForm) {

		Optional<User> opt = userRepository.findById(id);
		if (!opt.isPresent())
			throw new UserNotFoundException("Data not found");
		User user = User.convertCreateUserFormToUser(id, createUserForm);
		Set<Problem> problems = opt.get().getProblems();
		createUserForm.getProblems().stream().forEach(a -> problems.add(a));
		user.setProblems(problems);
		Set<Blog> blogs = opt.get().getBlogs();
		createUserForm.getBlogs().stream().forEach(a->blogs.add(a));
		Set<Course> courses = opt.get().getCourses();
		createUserForm.getCourses().stream().forEach(a->courses.add(a));
		
		userRepository.save(user);
		log.debug("User is added"); // log can be added in classes and enums
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.OK.value()).build();
	}

	@Override
	public APIResponseDTO getUserNameAndPassowrd(String pass) {
		// TODO Auto-generated method stub
		return null;
	}
  
}
