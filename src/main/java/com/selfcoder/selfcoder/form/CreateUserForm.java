package com.selfcoder.selfcoder.form;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.selfcoder.selfcoder.entity.Blog;
import com.selfcoder.selfcoder.entity.Course;
import com.selfcoder.selfcoder.entity.Problem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserForm {

	@NotBlank
    private String email;
	
	@NotBlank
    private String userName;
	 
	@NotBlank
	private String password;
	
    private String country;
	
	private String linkedin;
	
    private Set<Problem> problems;
    
    private Set<Blog> blogs;

    private Set<Course> courses;

}
