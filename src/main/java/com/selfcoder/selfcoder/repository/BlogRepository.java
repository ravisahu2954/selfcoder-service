package com.selfcoder.selfcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.selfcoder.selfcoder.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

}
