package com.workspace.blog.service;

import java.util.List;

import com.workspace.blog.entities.Post;
import com.workspace.blog.payloads.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	Post updatePost(PostDto postDto, Integer PostId);

	void deletePost(Integer PostId);

	List<PostDto> getAllPost();

	PostDto getPostById(Integer PostId);

	// get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);
	
}
