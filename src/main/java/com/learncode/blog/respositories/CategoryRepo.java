package com.learncode.blog.respositories;

import com.learncode.blog.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

	

}
