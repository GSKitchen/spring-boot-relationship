package com.example.relation.dto;

import java.util.List;

import com.example.relation.model.Post;
import com.example.relation.model.Tag;

public class PostByTagDto {
	
	private Tag tag;
	
	private List<Post> posts;
	
	public PostByTagDto() {
		super();
	}

	public PostByTagDto(Tag tag, List<Post> posts) {
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
