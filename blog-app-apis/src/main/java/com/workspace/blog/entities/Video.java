package com.workspace.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="videos")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VideoId;
    private String VideoName;
    private String VideoLocation;
    
    @ManyToOne
	private Post post;
	@ManyToOne
	private User user;
}
