package com.example.relation.dto.request;

import java.util.HashSet;
import java.util.Set;

import com.example.relation.model.Post;

public class PostDto {
	
	private Long userId;
	
	private Post post;
	
	private Set<Long> tagsId = new HashSet<>();

	public PostDto() {
		super();
	}

	public PostDto(Long userId, Post post, Set<Long> tagsId) {
		super();
		this.userId = userId;
		this.post = post;
		this.tagsId = tagsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Set<Long> getTagsId() {
		return tagsId;
	}

	public void setTagsId(Set<Long> tagsId) {
		this.tagsId = tagsId;
	}
}
