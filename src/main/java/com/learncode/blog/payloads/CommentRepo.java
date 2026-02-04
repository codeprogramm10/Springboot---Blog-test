package com.learncode.blog.payloads;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learncode.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	

}
