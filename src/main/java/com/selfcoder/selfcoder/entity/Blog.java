package com.selfcoder.selfcoder.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.selfcoder.selfcoder.form.UpdateBlogForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Blog implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name ="blog_seq" , initialValue = 1,allocationSize = 1,sequenceName = "blog_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	private Long userId;
	
	@Length(max = 100000)
	private String msg;

	@CreationTimestamp
	private LocalDate createAt;
	
	@UpdateTimestamp
	private LocalDate updateAt;
	
	@ManyToMany
	@JoinTable(
	   name = "blog_comm",
	   joinColumns = @JoinColumn(name = "blog_id" , referencedColumnName = "id"),
	   inverseJoinColumns = @JoinColumn (name = "comment_id" ,referencedColumnName = "id")
	)	
	private List<Comment> comment;

	public static Blog convertCreateBlogFormToBlog(Long id2, UpdateBlogForm updateBlogForm) {
		var blog = new Blog();
		blog.setId(id2);
		blog.setUserId(updateBlogForm.getUserId());
        blog.setMsg(updateBlogForm.getMsg());
        blog.setComment(updateBlogForm.getComment());
		return blog;
	}
	
}
	

