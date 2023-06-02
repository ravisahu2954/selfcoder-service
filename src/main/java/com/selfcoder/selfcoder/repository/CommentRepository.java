package com.selfcoder.selfcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfcoder.selfcoder.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

	
	
}
