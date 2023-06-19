package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
