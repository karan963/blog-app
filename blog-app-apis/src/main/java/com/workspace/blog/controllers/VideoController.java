package com.workspace.blog.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.payloads.VideoDto;
import com.workspace.blog.service.VideoService;

@RestController
@RequestMapping("/api/")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@PostMapping("/video/post/{postId}/user/{userId}/uploadVideo")
	public ResponseEntity<String> uploadNewVideo(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,VideoDto videoDto,@PathVariable Integer postId,@PathVariable Integer userId) throws IOException {
		this.videoService.uploadVideo(file, name, videoDto, postId, userId);
		return ResponseEntity.ok("Video Uploaded Succesfully");
	}
}
