package com.workspace.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workspace.blog.config.AppConstants;
import com.workspace.blog.entities.Role;
import com.workspace.blog.entities.User;
import com.workspace.blog.payloads.UserDto;
import com.workspace.blog.repositories.RoleRepo;
import com.workspace.blog.repositories.UserRepo;
import com.workspace.blog.security.CustomUserDetailService;
import com.workspace.blog.service.UserService;
import com.workspace.blog.exceptions.NonUniqueResultException;
import com.workspace.blog.exceptions.ResourceNotFoundException;
import com.workspace.blog.exceptions.UserAlreadyExistException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
//		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
//		User SavedUser =null;
//		if(this.userRepo.equals(userDto.)) {
//	        throw new UserAlreadyExistException("User with given username already exist");
//		}
//	    else {
//	    	User SavedUser = this.userRepo.save(user);
//	    	return this.userToDto(SavedUser);
//	    }
		User SavedUser = this.userRepo.save(user);
		return this.userToDto(SavedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// get
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		// set
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		// update
		User updatedUser = this.userRepo.save(user);
		UserDto UserDto1 = this.userToDto(updatedUser);
		return UserDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.getRoles().clear();
		this.userRepo.delete(user);

	}
	// manually adding data of one object or class to another object or class
//	public User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
//	}
//	//or
//	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		return userDto;
//	}

	// adding data of one object or class to another object or class using
	// ModelMapper
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User existingUser = this.findUserByEmail(userDto.getEmail());
		if(existingUser != null) {
			System.out.println("Hii I am Running");
			throw new UserAlreadyExistException("There is already an account registered with the same email");
//			throw new NonUniqueResultException("User Already exists");
		}
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public User findUserByEmail(String email) {
		Optional<User> findByEmail = userRepo.findByEmail(email);
		return this.modelMapper.map(findByEmail, User.class);
	}

}
