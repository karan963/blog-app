package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
}
