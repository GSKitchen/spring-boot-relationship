package com.example.relation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.dto.PostByTagDto;
import com.example.relation.model.Post;
import com.example.relation.model.Tag;
import com.example.relation.repository.TagRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@PostMapping("/tags")
	public Tag createTag(@Valid @RequestBody Tag tag) {
		return tagRepository.save(tag);
	}
	
	@GetMapping("/tags")
	public MappingJacksonValue getAllTag(){
		List<Tag> tags = tagRepository.findAll();
		MappingJacksonValue mapping = new MappingJacksonValue(tags);
		SimpleBeanPropertyFilter filterPost = SimpleBeanPropertyFilter.serializeAllExcept("tags");
		SimpleBeanPropertyFilter filterTag = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filterProvider = new SimpleFilterProvider()
											.addFilter("postFilter", filterPost)
											.addFilter("tagFilter", filterTag);
		mapping.setFilters(filterProvider);
		return mapping;
	}
}
