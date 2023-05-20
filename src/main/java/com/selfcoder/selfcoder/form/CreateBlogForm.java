package com.selfcoder.selfcoder.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBlogForm {

	 private Long userId;
	
	 private String msg;
	 
	

}
