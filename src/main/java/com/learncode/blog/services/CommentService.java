package com.learncode.blog.services;

import com.learncode.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	
	void deleteComment(Integer commentId);
	
	CommentDto updateComment(CommentDto commentDto, Integer postId);

}
