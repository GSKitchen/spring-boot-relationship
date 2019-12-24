package com.example.relation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.relation.model.Post;
import com.example.relation.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
	
}
