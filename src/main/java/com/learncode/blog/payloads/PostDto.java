package com.learncode.blog.payloads;

import java.util.Date;
import java.util.Set;

import com.learncode.blog.entities.Category;
import com.learncode.blog.entities.Comment;
import com.learncode.blog.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private Integer postId;
	
	private String postTitle;
	
	private String postContent;
	
	private String imageName;
	
	private Date postAddedDate;
	
	private Category category;
	
	private User user;
	
	private Set<Comment> comments;
	
}
