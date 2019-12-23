package com.example.relation.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.relation.model.Post;

public class PostDto {
	
	private Post post;
	
	private Set<Long> tagsId = new HashSet<>();

	public PostDto() {
		super();
	}

	public PostDto(Post post, Set<Long> tagsId) {
		super();
		this.post = post;
		this.tagsId = tagsId;
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
