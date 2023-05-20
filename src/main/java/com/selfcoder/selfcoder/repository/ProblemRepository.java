package com.selfcoder.selfcoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selfcoder.selfcoder.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem,Long>{

	Optional<Problem> findByTitle(String title);
}
