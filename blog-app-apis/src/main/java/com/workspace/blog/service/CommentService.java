package com.workspace.blog.service;

import com.workspace.blog.payloads.CommentDto;
import com.workspace.blog.payloads.PostDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	CommentDto updateComment(CommentDto commentDto, Integer commentId);
}
