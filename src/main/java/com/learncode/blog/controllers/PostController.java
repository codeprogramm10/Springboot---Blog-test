package com.learncode.blog.controllers;

import org.springframework.http.MediaType; 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learncode.blog.config.AppConstants;
import com.learncode.blog.entities.Post;
import com.learncode.blog.payloads.ApiResponse;
import com.learncode.blog.payloads.CategoryDto;
import com.learncode.blog.payloads.ImageResponse;
import com.learncode.blog.payloads.PostDto;
import com.learncode.blog.payloads.PostResponse;
import com.learncode.blog.services.CategoryService;
import com.learncode.blog.services.FileService;
import com.learncode.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//PUT create post
	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@Valid @RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId 
			)
	{
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);		
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
		
		
	}
	
	//GET get post by User
		@GetMapping("/users/{userId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByUser(
				@PathVariable Integer userId
				) 
		{
			
			List<PostDto> posts = this.postService.getPostsByUser(userId);
			return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
			
		}
		
		//GET get post by Category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByCategory(
				@PathVariable Integer categoryId
				) 
		{
			
			List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
			
		}
		
		//GET all posts
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPost(
				@RequestParam(value ="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
				@RequestParam(value = "pageSize", defaultValue= AppConstants.PAGE_SIZE, required = false) Integer pageSize,
				@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false ) String sortBy,
				@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
				)
		{
			
			PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
			return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
		}
		
		//GET post by Id
		
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@Valid @PathVariable("postId") Integer postId) {
	        PostDto postDto = this.postService.getPostById(postId);
	        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	    }
		
		//DELETE post by Id
		@DeleteMapping("/posts/{postId}")
		public ApiResponse deletePost(@PathVariable Integer postId)
		{
			this.postService.deletePost(postId);
			return new ApiResponse("Post is successfully deleted successfully", true);			
		}
		
		// PUT update the post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(
		        @Valid @RequestBody PostDto postDto,
		        @PathVariable("postId") int postId
		) {
		    PostDto updatedPost = this.postService.updatePost(postDto, postId);
		    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
		}
		
		//search 
		@GetMapping("/posts/search/{keyword}")
		public ResponseEntity<List<PostDto>> searchPost(
				@PathVariable("keyword") String keyword
				)
		{
			List<PostDto> result = this.postService.searchPost(keyword);
			return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
		}
		
		
		//post image upload
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto> uploadImage(
				@RequestParam("image") MultipartFile image,
				@PathVariable Integer postId
				) throws IOException
		{
			String fileName;
			fileName = this.fileService.uploadImage(path,  image);	
			PostDto postDto = this.postService.getPostById(postId);
			postDto.setImageName(fileName);
			PostDto updatedPost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
			
		}
		
		@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(
		        @PathVariable("imageName") String imageName,
		        HttpServletResponse response
		) throws IOException {

		    InputStream resource = this.fileService.getResource(path, imageName);

		    // Set content type
		    response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		    // Copy stream to response output stream
		    StreamUtils.copy(resource, response.getOutputStream());
		}

}
