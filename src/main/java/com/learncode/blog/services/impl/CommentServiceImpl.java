package com.learncode.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.learncode.blog.entities.Comment;
import com.learncode.blog.entities.Post;
import com.learncode.blog.exceptions.ResourceNotFoundException;
import com.learncode.blog.payloads.CommentDto;
import com.learncode.blog.payloads.CommentRepo;
import com.learncode.blog.respositories.PostRepo;
import com.learncode.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		//Find the post where to add comment
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedCommentContent = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedCommentContent,  CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
	    Comment comment = this.commentRepo.findById(commentId)
	        .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

	    this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
