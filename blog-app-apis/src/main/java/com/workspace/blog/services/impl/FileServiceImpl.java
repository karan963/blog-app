package com.workspace.blog.services.impl;

import java.io.File;
import com.workspace.blog.payloads.ImageDto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.entities.Image;
import com.workspace.blog.entities.Post;
import com.workspace.blog.entities.User;
import com.workspace.blog.exceptions.ResourceNotFoundException;
import com.workspace.blog.payloads.PostDto;
import com.workspace.blog.repositories.ImageRepo;
import com.workspace.blog.repositories.PostRepo;
import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ImageRepo imageRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;
	
//	@Autowired
//	private ImageDto imageDto;

	private static String UPLOADED_FOLDER = "K:\\Images\\";

	@Override
	public ImageDto uploadImage(String path, MultipartFile file,@ModelAttribute ImageDto imageDto,Integer postId,Integer userId) throws IOException, FileAlreadyExistsException {
		// File name
//		String name = file.getOriginalFilename();

		// randome name generate file
//		String randomID = UUID.randomUUID().toString();
//		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
//		MultipartFile filesname=this.modelMapper.map(fileName1, MultipartFile.class);
		// FullPath
//		String filePath = path + File.separator + fileName1;

		// create folder if not created
//		File f = new File(path);
//		if (!f.exists()) {
//			f.mkdir();
//		}

		// file copy
//		Files.copy(file.getInputStream(), Paths.get(filePath));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id",postId));
		Image image=this.modelMapper.map(imageDto, Image.class);
		image.setPost(post);
		image.setUser(user);
		byte[] bytes = file.getBytes();
		Path pathfile = Paths.get(path + file.getOriginalFilename());
		Files.write(pathfile, file.getBytes());
		
		String img = this.modelMapper.map(pathfile, String.class);
		image.setImageName(img);
		Image savedImage = this.imageRepo.save(image);
		return this.modelMapper.map(savedImage, ImageDto.class);
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

//	@Override
//	public ImageDto createImages(ImageDto imageDto,Integer postId,Integer userId) {
//		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id",userId));
//		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id",postId));
//		Image image=this.modelMapper.map(imageDto, Image.class);
//		image.setPost(post);
//		image.setUser(user);
//		Image savedImage = this.imageRepo.save(image);
//		return this.modelMapper.map(savedImage,ImageDto.class);
//	}

}
