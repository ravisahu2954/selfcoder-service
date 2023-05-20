package com.selfcoder.selfcoder.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column
	private Long userId;
	
	@Column
	private String msg;
	
	@CreationTimestamp
	private LocalDate createAt;
	
	@UpdateTimestamp
	private LocalDate updateAt;
}
