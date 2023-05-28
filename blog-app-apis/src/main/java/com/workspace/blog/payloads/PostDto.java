package com.workspace.blog.payloads;

import java.sql.Date;

import com.workspace.blog.entities.Category;
import com.workspace.blog.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
}
