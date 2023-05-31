package com.workspace.blog.service;

import java.util.List;

import com.workspace.blog.entities.Post;
import com.workspace.blog.payloads.PostDto;
import com.workspace.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer PostId);
	
	void deletePost(Integer PostId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer PostId);

	// get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
