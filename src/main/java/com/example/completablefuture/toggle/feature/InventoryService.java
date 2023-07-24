package com.example.completablefuture.toggle.feature;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
	
	public List<Product> getAllProducts() {
		return Stream.of(
				new Product(1, "mobile", 4000),
				new Product(2, "headphone", 2000),
				new Product(3, "watch", 14999),
				new Product(4, "glass", 900)
				).collect(Collectors.toList());
	}

}
