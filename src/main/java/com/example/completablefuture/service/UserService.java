package com.example.completablefuture.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.completablefuture.entity.User;
import com.example.completablefuture.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Async
	public CompletableFuture<List<User>> saveUser(MultipartFile file) {
		long start = System.currentTimeMillis();
		List<User> users = fetchListofEmployee();
		
		users = repository.saveAll(users);
		long end = System.currentTimeMillis();
		System.out.println("Endtime :: " + (end-start));
		return CompletableFuture.completedFuture(users);
		
	}
	
	@Async
	public CompletableFuture<List<User>> findAllUser() {
		System.out.println("" + Thread.currentThread().getName());
		List<User> users = repository.findAll();
		return CompletableFuture.completedFuture(users);
	}
	

	public List<User> fetchListofEmployee() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(new File("users.json"), new TypeReference<List<User>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
