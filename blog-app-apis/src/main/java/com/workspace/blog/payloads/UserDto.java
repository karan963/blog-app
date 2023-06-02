package com.workspace.blog.payloads;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.workspace.blog.entities.Image;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min=4 ,message="Username must be min of 4 characters")
	private String name;
	@Email(message="Email address is not valid!")
	private String email;
	@NotEmpty
	@Size(min=3,max=10,message="Password must be min of 4 chars and max of 10 chars !!")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<CommentDto> comments=new HashSet<>();
}
