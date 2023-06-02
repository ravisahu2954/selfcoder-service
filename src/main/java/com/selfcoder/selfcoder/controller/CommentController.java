package com.selfcoder.selfcoder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateCommentForm;
import com.selfcoder.selfcoder.form.UpdateCommentForm;
import com.selfcoder.selfcoder.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("selfcoder/v1/comments")
@RestController
@Slf4j
public class CommentController {

    private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<APIResponseDTO> addComment(final Long blogId,@Validated @RequestBody CreateCommentForm createCommentForm)
	{
	    log.info("addcomment");
		APIResponseDTO response = commentService.addComment(blogId,createCommentForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponseDTO> updateComment(@PathVariable Long id,@RequestBody UpdateCommentForm updateCommentForm)
	{
		APIResponseDTO response = commentService.updateComment(id, updateCommentForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
}
