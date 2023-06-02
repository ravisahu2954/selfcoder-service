package com.selfcoder.selfcoder.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCommentForm {

	@NotBlank
    private Long userId;
	
	@NotBlank
	private String msg;
	
}
