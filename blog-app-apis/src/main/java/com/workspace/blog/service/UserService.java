package com.workspace.blog.service;
import java.util.List;

import com.workspace.blog.entities.User;
import com.workspace.blog.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
//	UserDto doesUserExist(String email);
}
