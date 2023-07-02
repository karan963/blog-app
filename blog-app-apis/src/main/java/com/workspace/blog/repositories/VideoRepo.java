package com.workspace.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workspace.blog.entities.Video;

public interface VideoRepo extends JpaRepository<Video, Integer> {

}
