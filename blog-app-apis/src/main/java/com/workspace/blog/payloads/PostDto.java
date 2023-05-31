package com.workspace.blog.payloads;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;
import com.workspace.blog.entities.Comment;
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
	private Set<Comment> comments=new HashSet<>();
}
