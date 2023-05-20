package com.selfcoder.selfcoder.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.selfcoder.selfcoder.form.CreateUserForm;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, initialValue = 1, name = "user_seq")
	@GeneratedValue(generator = "user_seq",strategy = GenerationType.SEQUENCE)
	@Column(updatable = false , nullable = false)
	private Long id;
	
	@Column(unique = true , nullable = false)
    private String email;
	
	@Column(unique = true)
    private String userName;
	 
	@Column(nullable = false)
	private String password;
	
	@Column
    private String country;
	
	@Column
	private String linkedin;

	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
    @ManyToMany
    @JoinTable( 
			       name = "user_problem", 
	joinColumns = @JoinColumn
			       (
					  name = "user_id" ,referencedColumnName = "id"
				   ), 
	inverseJoinColumns  = @JoinColumn
	                 ( 
	                name = "problem_id" ,referencedColumnName = "id" 
	                ) 
			  )
	 
    private Set<Problem> problems;

    
    @ManyToMany
    @JoinTable( 
			       name = "user_blog", 
	joinColumns = @JoinColumn
			       (
					  name = "user_id" ,referencedColumnName = "id"
				   ), 
	inverseJoinColumns  = @JoinColumn
	                 ( 
	                name = "blog_id" ,referencedColumnName = "id" 
	                ) 
			  )
	 
    private Set<Blog> blogs;
    
    @ManyToMany
    @JoinTable( 
			       name = "user_course", 
	joinColumns = @JoinColumn
			       (
					  name = "user_id" ,referencedColumnName = "id"
				   ), 
	inverseJoinColumns  = @JoinColumn
	                 ( 
	                name = "course_id" ,referencedColumnName = "id" 
	                ) 
			  )
	 
    private Set<Course> courses;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

	public static User convertCreateUserFormToUser(Long id2, CreateUserForm createUserForm) {
		var user = new User();
		user.setId(id2);
		user.setEmail(createUserForm.getEmail());
        user.setUserName(createUserForm.getUserName());
        user.setPassword(createUserForm.getPassword());
        user.setCountry(createUserForm.getCountry());
        user.setLinkedin(createUserForm.getLinkedin());
		return user;
	}

}   
