package com.selfcoder.selfcoder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateCourseForm;
import com.selfcoder.selfcoder.form.UpdateCourseForm;
import com.selfcoder.selfcoder.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@RequestMapping("selfcoder/v1/courses")
@RestController
@Slf4j
public class CourseController {

	private final CourseService courseService;
	
	@PostMapping
	public ResponseEntity<APIResponseDTO> addCourse(@RequestBody CreateCourseForm createCourseForm)
	{
	    log.info("addcourse");
		APIResponseDTO response = courseService.addCourse(createCourseForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@GetMapping
	public ResponseEntity<APIResponseDTO> getCourses(@RequestParam(name = "page" , defaultValue = "0")  Integer page,@RequestParam(name = "size" , defaultValue = "10") Integer size)
	{
		APIResponseDTO response = courseService.getCourses(page,size);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDTO> getCourses(@PathVariable Long id)
	{
		APIResponseDTO response = courseService.getCoursesById(id);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponseDTO> updateCourse(@PathVariable Long id,@RequestBody UpdateCourseForm updateCourseForm)
	{
		APIResponseDTO response = courseService.updateCourse(id, updateCourseForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
}
