package com.selfcoder.selfcoder.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.selfcoder.selfcoder.form.UpdateCommentForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Comment {

	@Id
	@SequenceGenerator(name ="comm_seq" , initialValue = 1,allocationSize = 1,sequenceName = "comm_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false) 
	private Long userId;
	
	@Column(nullable = false)
	private String msg;
	
	@CreationTimestamp
	private LocalDate createAt;
	
	@UpdateTimestamp
	private LocalDate updateAt;

	public static Comment convertCreateCommentFormToComment(Long id2, UpdateCommentForm updateCommentForm) {

		var comment = new Comment();
		comment.setId(id2);
		comment.setUserId(updateCommentForm.getUserId());
		comment.setMsg(updateCommentForm.getMsg());
		return comment;
	
	}
}
