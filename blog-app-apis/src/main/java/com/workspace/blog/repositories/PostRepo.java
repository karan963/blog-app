package com.workspace.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Category;
import com.workspace.blog.entities.Post;
import com.workspace.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	// retrieve users all posts
	List<Post> findByUser(User user);

	// retrieve all posts of specific categories
	List<Post> findByCategory(Category category);
}
