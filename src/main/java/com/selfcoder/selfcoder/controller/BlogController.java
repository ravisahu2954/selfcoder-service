package com.selfcoder.selfcoder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateBlogForm;
import com.selfcoder.selfcoder.form.UpdateBlogForm;
import com.selfcoder.selfcoder.service.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@RequestMapping("selfcoder/v1/blogs")
@RestController
@Slf4j
public class BlogController {

	private final BlogService blogService;
	
	@PostMapping
	public ResponseEntity<APIResponseDTO> addBlog(@Validated @RequestBody CreateBlogForm createBlogForm)
	{
	    log.info("addblog");
		APIResponseDTO response = blogService.addBlog(createBlogForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@GetMapping
	public ResponseEntity<APIResponseDTO> getBlogs(@RequestParam(name = "page" , defaultValue = "0")  Integer page,@RequestParam(name = "size" , defaultValue = "10") Integer size)
	{
		APIResponseDTO response = blogService.getBlogs(page,size);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDTO> getBlogs(@Validated @PathVariable Long id)
	{
		APIResponseDTO response = blogService.getBlogsById(id);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponseDTO> updateBlog(@PathVariable Long id,@RequestBody UpdateBlogForm updateBlogForm)
	{
		APIResponseDTO response = blogService.updateBlog(id, updateBlogForm);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
}
