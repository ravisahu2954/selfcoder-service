package com.selfcoder.selfcoder.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.selfcoder.selfcoder.form.UpdateCourseForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {

	@Id
	@SequenceGenerator(allocationSize = 1,initialValue = 0, name = "")
	@GeneratedValue
	@Column(updatable = false , nullable = false)
	private Long id;
	
    @Column(nullable = false)
    private String courseName;
	
    @Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public static Course convertCreateCourseFormToCourse(Long id2, UpdateCourseForm updateCourseForm) {
	
		var course = new Course();
		course.setId(id2);
		course.setCourseName(updateCourseForm.getCourseName());
		return course;
	}
}
