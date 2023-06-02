package com.workspace.blog.controllers;

import java.io.IOException;


import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.entities.Post;
import com.workspace.blog.payloads.ApiResponse;
import com.workspace.blog.payloads.ImageDto;
import com.workspace.blog.payloads.PostDto;
import com.workspace.blog.service.FileService;
import com.workspace.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class ImageController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Value("${project.image}")
//	private String path;
	private static String path = "K:\\Images\\";
	
	@PostMapping("/image/post/{postId}/user/{userId}/upload")
	public ResponseEntity<ImageDto> uploadPostImage(@RequestParam("image") MultipartFile image,ImageDto imageDto ,@PathVariable Integer postId,@PathVariable Integer userId) throws IOException,FileAlreadyExistsException{
		ImageDto uploadImage = this.fileService.uploadImage(path, image, imageDto, postId, userId);
		return new ResponseEntity<ImageDto>(uploadImage,HttpStatus.CREATED);
	}
	
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
