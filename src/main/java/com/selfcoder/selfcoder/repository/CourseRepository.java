package com.selfcoder.selfcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfcoder.selfcoder.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
