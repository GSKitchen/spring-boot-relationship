package com.example.relation.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "posts")
public class Post extends AuditModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	private String title;
	
	@NotNull
	@Column(unique = true)
	private String slug;
	
	private String description;
	
	@NotNull
	@Lob
	private String content;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
			)
	@JoinTable(
			name = "post_tag",
			joinColumns = { @JoinColumn(name="post_id")},
			inverseJoinColumns = {@JoinColumn(name="tag_id")}
			)
	private Set<Tag> tags = new HashSet<>();

	
	public Post() {
		super();
	}


	public Post(String title, String slug, String description, String content) {
		super();
		this.title = title;
		this.slug = slug;
		this.description = description;
		this.content = content;
	}
	
	public Post(String title, String slug, String description, String content, Set<Tag> tags) {
		super();
		this.title = title;
		this.slug = slug;
		this.description = description;
		this.content = content;
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


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
