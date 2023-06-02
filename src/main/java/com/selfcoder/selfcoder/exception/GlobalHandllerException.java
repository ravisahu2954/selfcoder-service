package com.selfcoder.selfcoder.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.selfcoder.selfcoder.dto.APIResponseDTO;

@RestControllerAdvice
public class GlobalHandllerException {

	@ExceptionHandler(UserAlreadyExist.class)
	ResponseEntity<APIResponseDTO> userAlreadyExist(UserAlreadyExist userAlreadyExist)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(userAlreadyExist.getMessage())
				.success(false)
				.status(HttpStatus.BAD_REQUEST.value())
				.build(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<APIResponseDTO> userNotFoundException(UserNotFoundException userNotFoundException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(userNotFoundException.getMessage())
				.success(false)
				.status(HttpStatus.NOT_FOUND.value())
				.build(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ProblemAlreadyExistException.class)
	ResponseEntity<APIResponseDTO> problemAlreadyExistException(ProblemAlreadyExistException problemAlreadyExist)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(problemAlreadyExist.getMessage())
				.success(false)
				.status(HttpStatus.BAD_REQUEST.value())
				.build(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProblemNotFoundException.class)
	ResponseEntity<APIResponseDTO> problemNotFoundException(ProblemNotFoundException problemNotFoundException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(problemNotFoundException.getMessage())
				.success(false)
				.status(HttpStatus.NOT_FOUND.value())
				.build(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CourseNotFoundException.class)
	ResponseEntity<APIResponseDTO> courseNotFoundException(CourseNotFoundException courseNotFoundException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(courseNotFoundException.getMessage())
				.success(false)
				.status(HttpStatus.NOT_FOUND.value())
				.build(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogNotFoundException.class)
	ResponseEntity<APIResponseDTO> blogNotFoundException(BlogNotFoundException blogmNotFoundException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.timeStamp(System.currentTimeMillis())
				.message(blogmNotFoundException.getMessage())
				.success(false)
				.status(HttpStatus.NOT_FOUND.value())
				.build(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value= {DataAccessException.class,Exception.class})
	public ResponseEntity<APIResponseDTO> handleDataAccessException(Exception dataAccessException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.message(dataAccessException.getMessage())
				.timeStamp(System.currentTimeMillis())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }	

}
