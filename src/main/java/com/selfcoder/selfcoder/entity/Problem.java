package com.selfcoder.selfcoder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Problem {

	@Id
	@SequenceGenerator(name ="pro_seq" , initialValue = 1,allocationSize = 1,sequenceName = "pro_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false,nullable = false)
    private long id;
	
    @Column(nullable = false , unique = true)
	private String title;
	
    @Column(nullable = false)
    private String link;
    
	@Column(nullable = false)
	private String tag;
	
	@Column(nullable = false)
	private String acceptance;
	
	@Column(nullable = false)
	private String difficulty;
	
	@Column(nullable = false)
	private String solution;
	
}
