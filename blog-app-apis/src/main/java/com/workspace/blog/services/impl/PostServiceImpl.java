package com.workspace.blog.services.impl;


import java.util.List;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.workspace.blog.entities.Category;
import com.workspace.blog.entities.Post;
import com.workspace.blog.entities.User;
import com.workspace.blog.exceptions.ResourceNotFoundException;
import com.workspace.blog.payloads.PostDto;
import com.workspace.blog.payloads.PostResponse;
import com.workspace.blog.repositories.CategoryRepo;
import com.workspace.blog.repositories.PostRepo;
import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		Date date = new Date();
		post.setAddedDate(date);
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer PostId) {
		Post post =this.postRepo.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id",PostId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer PostId) {
		Post post =this.postRepo.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id",PostId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort=Sort.by(sortBy).ascending();
//		}
//		else {
//			sort=Sort.by(sortBy).descending();
//		}
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer PostId) {
		Post post = this.postRepo.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id",PostId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.searchByTitle(keyword);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
