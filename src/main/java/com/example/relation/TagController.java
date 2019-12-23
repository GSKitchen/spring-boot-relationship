package com.example.relation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.model.Tag;
import com.example.relation.repository.TagRepository;

@RestController
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@PostMapping("/tags")
	public Tag createTag(@Valid @RequestBody Tag tag) {
		return tagRepository.save(tag);
	}
	
	@GetMapping("/tags")
	public List<Tag> getAllTag(){
		return tagRepository.findAll();
	}
}
