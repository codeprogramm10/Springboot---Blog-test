package com.learncode.blog.services;

import java.util.List;

import com.learncode.blog.entities.Post;
import com.learncode.blog.payloads.CategoryDto;
import com.learncode.blog.payloads.PostDto;
import com.learncode.blog.payloads.PostResponse;

public interface PostService {
	
	//create
		PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
		
		//update 
		PostDto updatePost(PostDto postDto, int postId);
		
		//delete
		public void deletePost(Integer postId);
		
		//get single post
		
		PostDto getPostById(Integer postId);
		
		//get All post
		PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
		
		//get post by category
		
		List<PostDto> getPostsByCategory(Integer categoryId);
		
		//get all posts by users
		List<PostDto> getPostsByUser(Integer userId);
		
		//search post
		List<PostDto> searchPost(String keyword);
}
