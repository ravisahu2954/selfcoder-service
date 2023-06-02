package com.selfcoder.selfcoder.service;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateCommentForm;
import com.selfcoder.selfcoder.form.UpdateCommentForm;

public interface CommentService {

	APIResponseDTO addComment(Long blogId,CreateCommentForm createCommentForm);

	APIResponseDTO deleteCommentByIds(Long id); 
	
	APIResponseDTO updateComment(Long id,UpdateCommentForm updateCommentForm);
		
}
