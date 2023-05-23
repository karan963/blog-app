package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
