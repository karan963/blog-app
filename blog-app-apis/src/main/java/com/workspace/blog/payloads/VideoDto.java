package com.workspace.blog.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDto {
	private Long VideoId;
    private String VideoName;
    private String VideoLocation;
    private PostDto postDto;
	private UserDto userDto;
}
