package com.selfcoder.selfcoder.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.selfcoder.selfcoder.dto.APIResponseDTO;
import com.selfcoder.selfcoder.entity.Blog;
import com.selfcoder.selfcoder.entity.Comment;
import com.selfcoder.selfcoder.form.CreateCommentForm;
import com.selfcoder.selfcoder.form.UpdateCommentForm;
import com.selfcoder.selfcoder.repository.BlogRepository;
import com.selfcoder.selfcoder.repository.CommentRepository;
import com.selfcoder.selfcoder.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository commentRepository;
	
	private final ModelMapper modelMapper;
	
	private BlogRepository blogRepository;
	
	@Override
	public APIResponseDTO addComment(Long blogId,CreateCommentForm createCommentForm) {
		
		log.info("addComment");

		Comment comment = modelMapper.map(createCommentForm, Comment.class);
		
		commentRepository.save(comment);
		Optional<Blog> blog = blogRepository.findById(blogId);
		//blog.get().setId(blogId);
		blog.get().getComment().add(comment);
		blogRepository.save(blog.get());
		log.debug("Comment is added");
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.CREATED.value()).build();
	
	}

	@Override
	public APIResponseDTO deleteCommentByIds(Long id) {
		
		log.info("## Deleting Comment ## {}", id);
		
		log.info("## Comment deleted ##");
		return APIResponseDTO.builder().message("Comment deleted").timeStamp(System.currentTimeMillis()).success(true)
				.status(HttpStatus.OK.value()).build();
		
		
	}

	@Override
	public APIResponseDTO updateComment(Long id, UpdateCommentForm updateCommentForm) {
		
		Comment comment = Comment.convertCreateCommentFormToComment(id, updateCommentForm);
		commentRepository.save(comment);
		log.debug("Comment is added"); // log can be added in classes and enums
		return APIResponseDTO.builder().timeStamp(System.currentTimeMillis()).message("Set Data").success(true)
				.status(HttpStatus.OK.value()).build();
		
	}
   	
}
