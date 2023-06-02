package com.workspace.blog.payloads;

import java.sql.Date;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.entities.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
//	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>();
//	private List<Image> image=new ArrayList<>();
}
