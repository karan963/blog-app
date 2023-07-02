package com.workspace.blog.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.entities.Image;
import com.workspace.blog.entities.Post;
import com.workspace.blog.entities.User;
import com.workspace.blog.entities.Video;
import com.workspace.blog.exceptions.ResourceNotFoundException;
import com.workspace.blog.payloads.VideoDto;
import com.workspace.blog.repositories.PostRepo;
import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.repositories.VideoRepo;
import com.workspace.blog.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private VideoRepo videoRepo;
	
	private final String VIDEO_UPLOAD_DIR = "C:\\Users\\pawar\\git\\blog-app\\blog-app-apis\\src\\main\\resources\\static\\videos\\";
	
	@Override
	public void uploadVideo(MultipartFile file, String name, VideoDto videoDto, Integer postId,
			Integer userId) throws IOException {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id",postId));
		
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// Save the video file to the server
		String fileLocation = VIDEO_UPLOAD_DIR + fileName;
		// You may need to handle potential exceptions here, such as IOException
		Path filePath = Path.of(VIDEO_UPLOAD_DIR, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Video video=this.modelMapper.map(videoDto, Video.class);
		video.setPost(post);
		video.setUser(user);
		video.setVideoName(fileName);
		video.setVideoLocation(VIDEO_UPLOAD_DIR + fileName);
        videoRepo.save(video);
	}

	

}
