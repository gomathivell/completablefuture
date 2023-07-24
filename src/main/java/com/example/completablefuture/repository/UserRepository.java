package com.example.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.completablefuture.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
