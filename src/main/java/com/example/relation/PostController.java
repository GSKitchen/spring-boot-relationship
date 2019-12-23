package com.example.relation;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.exception.ResourceNotFoundException;
import com.example.relation.model.Post;
import com.example.relation.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(value = "/posts")
	public Page<Post> getAllPost(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	
	@GetMapping("/posts/{postId}")
	public Post getPost(@PathVariable String postId) {
		Optional<Post> optional = postRepository.findById(Long.valueOf(postId));
		return optional.get();
	}
	
	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId){
		return postRepository.findById(postId).map(post -> {
			postRepository.delete(post);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Post id -> " + postId));
	}

}
