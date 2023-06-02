package com.selfcoder.selfcoder.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProblemForm {
		
	    @NotBlank(message = "title is required")
		private String title;
		
	    @NotBlank(message = "link is required")
		private String link;
		
	    @NotBlank(message = "tag is required")
        private String Tag;
		
		@NotBlank(message = "acceptance is required")
	    private String acceptance;
		
		@NotBlank(message = "difficulty is required")
		private String difficulty;
		
		@NotBlank(message = "frquency is required")
		private String Solution;
	
}
