package com.selfcoder.selfcoder.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCourseForm {

	 @NotBlank(message = "course name is required")
	 private String courseName;
	
}
