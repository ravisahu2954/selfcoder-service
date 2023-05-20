package com.selfcoder.selfcoder.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.dto.CourseDTO;
import com.selfcoder.selfcoder.exception.CourseNotFoundException;
import com.selfcoder.selfcoder.entity.Course;
import com.selfcoder.selfcoder.exception.CustomBadRequestException;
import com.selfcoder.selfcoder.form.CreateCourseForm;
import com.selfcoder.selfcoder.form.UpdateCourseForm;
import com.selfcoder.selfcoder.repository.CourseRepository;
import com.selfcoder.selfcoder.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
 
	private final ModelMapper modelMapper;
	
	@Override
	public APIResponseDTO addCourse(CreateCourseForm createCourseForm) {

		log.info("addCourse");

		Course course = modelMapper.map(createCourseForm, Course.class);
		courseRepository.save(course);
		log.debug("Course is added");
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.CREATED.value()).build();
	}

	@Override
	public APIResponseDTO getCourses(Integer page, Integer size) {

		Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
		Page<Course> listOfCourse = courseRepository.findAll(PageRequest.of(page, size, sort));
		List<CourseDTO> listDTO = null;
		if (listOfCourse.isEmpty())
			throw new CourseNotFoundException("Data not found");
		else {
			listDTO = listOfCourse.stream().map(u -> modelMapper.map(u, CourseDTO.class)).collect(Collectors.toList());
		}
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).data(listDTO).message("data found")
				.success(true).status(HttpStatus.OK.value()).build();

	}

	@Override
	public APIResponseDTO deleteCourseByIds(List<Long> ids) {

		log.info("## Deleting Course ## {}", ids);
		if (ids == null) {
			throw new CustomBadRequestException("ids cannot be null!");
		}
		try {
			courseRepository.deleteAllById(ids);
		} catch (EmptyResultDataAccessException e) {
			throw new CourseNotFoundException("data not found");
		}
		log.info("## Course deleted ##");
		return APIResponseDTO.builder().message("Course deleted").timeStamp(System.currentTimeMillis()).success(true)
				.status(HttpStatus.OK.value()).build();

	}

	@Override
	public APIResponseDTO updateCourse(Long id, UpdateCourseForm updateCourseForm) {

		Optional<Course> opt = courseRepository.findById(id);
		if (!opt.isPresent())
			throw new CourseNotFoundException("Data not found");
		Course course = Course.convertCreateCourseFormToCourse(id, updateCourseForm);
		courseRepository.save(course);
		log.debug("Course is added"); // log can be added in classes and enums
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.OK.value()).build();

	}

	@Override
	public APIResponseDTO getCoursesById(Long id) {

		Optional<Course> b = courseRepository.findById(id);
		if (b.isPresent()) {
			return APIResponseDTO.builder().timeStamp(System.currentTimeMillis())
					.data(modelMapper.map(b.get(), CourseDTO.class)).message("data found").success(true)
					.status(HttpStatus.OK.value()).build();
		} else
			throw new CourseNotFoundException("Data not found");

	}
   
}
