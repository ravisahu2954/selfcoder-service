package com.selfcoder.selfcoder.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCourseForm {

	 @NotBlank(message = "course name is required")
	 private String courseName;
	
}

