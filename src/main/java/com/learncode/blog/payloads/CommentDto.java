package com.learncode.blog.payloads;

import com.learncode.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private int commentId;
	
	private Post post;
	
	private String commentContent;

}
