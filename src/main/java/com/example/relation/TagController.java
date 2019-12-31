package com.example.relation;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.dto.response.PostByTagDto;
import com.example.relation.model.Post;
import com.example.relation.model.Tag;
import com.example.relation.repository.TagRepository;

@RestController
@CrossOrigin(origins = "*")
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@PostMapping("/tags")
	public Tag createTag(@Valid @RequestBody Tag tag) {
		return tagRepository.save(tag);
	}
	
	@GetMapping("/tags")
	public Slice<Tag> getAllTag(Pageable pageable){
		return tagRepository.findAll(pageable);
	}
	
	
	@GetMapping("/tags/{tagId}")
	public PostByTagDto getPostByTag(@PathVariable Long tagId){
		Tag tag = tagRepository.findById(tagId).get();
		Set<Post> posts = tag.getPosts();
		PostByTagDto respDto = new PostByTagDto();
		respDto.setTag(tag);
		respDto.setPosts(posts);
		
		return respDto;
	}
}
