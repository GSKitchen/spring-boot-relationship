package com.example.relation.dto.response;

import java.util.HashSet;
import java.util.Set;

import com.example.relation.model.User;

public class PostResponse {
	
	private Long id;
	
	private String title;
	
	private String slug;
	
	private String description;
	
	private String content;
	
	private User user;
	
	private Set<TagResponse> tags = new HashSet<>();

	public PostResponse() {
		super();
	}

	public PostResponse(Long id, String title, String slug, String description, String content, User user,
			Set<TagResponse> tags) {
		super();
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.description = description;
		this.content = content;
		this.user = user;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<TagResponse> getTags() {
		return tags;
	}

	public void setTags(Set<TagResponse> tags) {
		this.tags = tags;
	}
	
	

}
