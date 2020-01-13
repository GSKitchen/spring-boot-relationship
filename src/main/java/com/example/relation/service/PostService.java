package com.example.relation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.relation.model.Post;

public interface PostService {

	public Post findById(Long postId);
	
	public Page<Post> getAllPost(Pageable pageable);
	
	public void deletePostById(Long postId);
}
