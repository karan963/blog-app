package com.workspace.blog.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.payloads.VideoDto;

public interface VideoService {
	public void uploadVideo(MultipartFile file,String name,VideoDto videoDto ,Integer postId,Integer userId) throws IOException;
}
