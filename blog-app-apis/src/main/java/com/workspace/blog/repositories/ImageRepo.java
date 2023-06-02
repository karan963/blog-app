package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {

}
