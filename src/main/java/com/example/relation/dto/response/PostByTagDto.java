package com.example.relation.dto.response;

import java.util.List;
import java.util.Set;

import com.example.relation.model.Post;
import com.example.relation.model.Tag;

public class PostByTagDto {
	
	private Tag tag;
	
	private Set<Post> posts;
	
	public PostByTagDto() {
		super();
	}

	public PostByTagDto(Tag tag, Set<Post> posts) {
		super();
		this.tag = tag;
		this.posts = posts;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}
