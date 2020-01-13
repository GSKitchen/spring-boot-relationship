package com.example.relation.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.relation.exception.ResourceNotFoundException;
import com.example.relation.model.Post;
import com.example.relation.model.Tag;
import com.example.relation.repository.PostRepository;
import com.example.relation.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private RedisTemplate<String, Post> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, Tag> redisTagTemplate;
	
	@Override
	public Post findById(Long postId) {
		final String key = "post_" + postId;
		ValueOperations<String, Post> operations = redisTemplate.opsForValue();
		ValueOperations<String, Tag> tagOperations = redisTagTemplate.opsForValue();
		final boolean hasKey = redisTemplate.hasKey(key);
		
		if(hasKey) {
			System.out.println("haskey found");
			System.out.println(operations.get(key).toString());
			Post post = operations.get(key);
			System.out.println("cache post found");
			//post.setTags(null);
			//set tag from cache
			/*
			List<Long> tagsId = post.getTags().stream()
				.map(tag->tag.getId())
				.collect(Collectors.toList());
			Set<Tag> cachedTag = tagsId.stream().map(id-> {
				cachedTag.add(tagOperations.get("tag_" + id));
			}).collect(Collectors.toSet()); */
			
			System.out.println("from cache --- " + key);
			return post;
		}
		Optional<Post> postOptional = postRepository.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			Set<Tag> tags = post.getTags();
			operations.set(key, post);
			tags.forEach(tag ->{
				tagOperations.set("tag_" + tag.getId(), tag);
				System.out.println("tag" + tag.getId() + "inseted into cache");
			});
			System.out.println("insert to cache " + key);
			return post;
		}else {
			throw new ResourceNotFoundException("Post Not found: " + postId);
		}
	}

	@Override
	public Page<Post> getAllPost(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public void deletePostById(Long postId) {
		String key = "post_" + postId;
		boolean hasKey = redisTemplate.hasKey(key);
		if(hasKey) {
			redisTemplate.delete(key);
			System.out.println("delte form cache " + key);
		}
		postRepository.deleteById(postId);
	}

}
