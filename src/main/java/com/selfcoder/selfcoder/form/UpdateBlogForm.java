package com.selfcoder.selfcoder.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.selfcoder.selfcoder.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBlogForm {

	 private String msg;
	 
	 private Long userId;
	 
	 private List<Comment> comment;
	
}
