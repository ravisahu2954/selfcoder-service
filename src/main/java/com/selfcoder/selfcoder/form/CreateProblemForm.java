package com.selfcoder.selfcoder.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProblemForm {
		
		private String title;
		
		private String link;
		
		private String Tag;
		
		private String acceptance;
		
		private String difficulty;
		
		private String frequency;
	
}
