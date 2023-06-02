package com.selfcoder.selfcoder.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCommentForm {

	@NotBlank
    private Long userId;
	
	@NotBlank
	private String msg;
	
}
