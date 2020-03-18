package com.example.relation;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.relation.dto.request.PostDto;
import com.example.relation.dto.response.PostResponse;
import com.example.relation.exception.ResourceNotFoundException;
import com.example.relation.model.Post;
import com.example.relation.model.Tag;
import com.example.relation.model.User;
import com.example.relation.repository.PostRepository;
import com.example.relation.repository.TagRepository;
import com.example.relation.repository.UserRepository;
import com.example.relation.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private UserRepository userRepository;  
	
	@Autowired
	private PostService postService;

	@GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Post> getAllPost(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit) {
		Pageable pageable = PageRequest.of(pageNo, limit);
		return postService.getAllPost(pageable);
	}

	@GetMapping("/posts/{postId}")
	public PostResponse getPost(@PathVariable Long postId) {
		return postService.findById(postId);
	}

	@PostMapping("/posts")
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto pd) {
		User user = userRepository.getOne(pd.getUserId());
		Post post = pd.getPost();
		System.out.println("===============================================");
		System.out.println(pd.getTagsId());
		System.out.println("===============================================");
		
		post.setUser(user);
		
		for(Long tagId: pd.getTagsId()) {
			Tag tag = tagRepository.getOne(tagId);
			post.getTags().add(tag);
			tag.getPosts().add(post);
		}
		
		/*
		List<Tag> tagList = pd.getTagsId().stream().map(tagId -> {
			Tag tag = tagRepository.getOne(tagId);
			post.getTags().add(tag);
			tag.getPosts().add(post);
		}).collect(Collectors.toList());
		
		tagRepository.saveAll(tagList); */
		post.setSlug(ExtraTools.toSlug(post.getTitle()));
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	 

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId) {
		return postRepository.findById(postId).map(post -> {
			postRepository.delete(post);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Post id -> " + postId));
	}

}
