package com.selfcoder.selfcoder.service;

import java.util.List;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateCourseForm;
import com.selfcoder.selfcoder.form.UpdateCourseForm;

public interface CourseService {
 
	APIResponseDTO addCourse(CreateCourseForm createCourseForm);

	APIResponseDTO getCourses(Integer page,Integer size);
	
	APIResponseDTO deleteCourseByIds(List<Long> ids); 
	
	APIResponseDTO updateCourse(Long id,UpdateCourseForm updateCourseForm);
	
	APIResponseDTO getCoursesById(Long id);
}
