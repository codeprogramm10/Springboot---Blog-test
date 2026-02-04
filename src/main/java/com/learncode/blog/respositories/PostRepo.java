package com.learncode.blog.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learncode.blog.entities.Category;
import com.learncode.blog.entities.Post;
import com.learncode.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>  {
	
	// Fetch posts by category
    List<Post> findByCategory(Category category);
    
    // Fetch posts by user
    List<Post> findByUser(User user);
    
    @Query("select p from Post p where p.postTitle like :key")
    List<Post> searchByPostTitle(@Param("key") String postTitle);

}
