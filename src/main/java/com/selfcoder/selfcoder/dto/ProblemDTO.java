package com.selfcoder.selfcoder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) 
public class ProblemDTO {

	    private long id;
		
		private String title;
		
		private String link;
		
		private String tag;
		
		private String acceptance;
		
		private String difficulty;
		
		private String frequency;
	
}
