package com.workspace.blog.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.web.multipart.MultipartFile;

import com.workspace.blog.payloads.ImageDto;
import com.workspace.blog.payloads.PostDto;

public interface FileService {
	ImageDto uploadImage(String path,MultipartFile file,ImageDto imageDto,Integer postId,Integer userId) throws IOException,FileAlreadyExistsException;
	
	InputStream getResource(String path,String fileName) throws FileNotFoundException;
	
//	ImageDto createImages(ImageDto imageDto,Integer postId,Integer userId);
}
