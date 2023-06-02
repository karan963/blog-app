package com.workspace.blog.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
	private int imageId;
	private String imageName;
	private PostDto post;
	private UserDto userDto;
}
