package com.example.relation.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relation.dto.response.PostResponse;
import com.example.relation.dto.response.TagResponse;
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
	private RedisTemplate<String, PostResponse> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, Tag> redisTagTemplate;
	
	@Override
	@Transactional
	public PostResponse findById(Long postId) {
		final String key = "post_" + postId;
		ValueOperations<String, PostResponse> operations = redisTemplate.opsForValue();
		final boolean hasKey = redisTemplate.hasKey(key);
		
		if(hasKey) {
			PostResponse post = operations.get(key);
			
			System.out.println("from cache --- " + key);
			return post;
		}
		Optional<Post> postOptional = postRepository.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			PostResponse postResponse = new PostResponse();
			postResponse.setId(post.getId());
			postResponse.setContent(post.getContent());
			postResponse.setDescription(post.getDescription());
			postResponse.setSlug(post.getSlug());
			postResponse.setTags(post.getTags().stream().map(tag -> {
				TagResponse dto = new TagResponse();
				dto.setId(tag.getId());
				dto.setName(tag.getName());
				return dto;
			}).collect(Collectors.toSet()));
			postResponse.setTitle(post.getTitle());
			postResponse.setUser(null);
			
			operations.set(key, postResponse);
			System.out.println("insert to cache " + key);
			return postResponse;
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
