package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
