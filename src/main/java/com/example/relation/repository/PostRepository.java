package com.example.relation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.relation.model.Post;
import com.example.relation.model.Tag;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
