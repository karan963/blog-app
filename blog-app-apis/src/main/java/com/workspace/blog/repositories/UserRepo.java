package com.workspace.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.User;
import com.workspace.blog.payloads.UserDto;

public interface UserRepo extends JpaRepository<User, Integer>{
	UserDto findByEmail(String email);
}
