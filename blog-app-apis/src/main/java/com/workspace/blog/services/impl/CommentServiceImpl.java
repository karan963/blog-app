package com.workspace.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workspace.blog.entities.Post;
import com.workspace.blog.entities.User;
import com.workspace.blog.entities.Comment;
import com.workspace.blog.exceptions.ResourceNotFoundException;
import com.workspace.blog.payloads.CommentDto;
import com.workspace.blog.repositories.CommentRepo;
import com.workspace.blog.repositories.PostRepo;
import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId",postId));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId",userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepo.save(comment);
		return this .modelMapper.map(savedComment, CommentDto.class);
	}
	
	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId",commentId));
		this.commentRepo.delete(com);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId",commentId));
		com.setContent(commentDto.getContent());
		Comment updatedComment = this.commentRepo.save(com);
		return this.modelMapper.map(updatedComment,CommentDto.class);
	}

}
