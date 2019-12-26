package com.example.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
