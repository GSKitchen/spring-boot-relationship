package com.example.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
