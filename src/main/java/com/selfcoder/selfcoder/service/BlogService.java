package com.selfcoder.selfcoder.service;

import java.util.List;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.form.CreateBlogForm;
import com.selfcoder.selfcoder.form.UpdateBlogForm;

public interface BlogService {
 
	APIResponseDTO addBlog(CreateBlogForm createBlogForm);

	APIResponseDTO getBlogs(Integer page,Integer size);
	
	APIResponseDTO deleteBlogByIds(List<Long> ids); 
	
	APIResponseDTO updateBlog(Long id,UpdateBlogForm updateBlogForm);
	
	APIResponseDTO getBlogsById(Long id);
}
