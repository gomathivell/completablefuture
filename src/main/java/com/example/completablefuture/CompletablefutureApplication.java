package com.example.completablefuture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import com.example.completablefuture.entity.User;
import com.example.completablefuture.toggle.feature.InventoryService;
import com.example.completablefuture.toggle.feature.Product;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
@RestController
public class CompletablefutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompletablefutureApplication.class, args);
	}
	
	@Autowired
	private FeatureManager  manager;
	
	public static final Feature DISCOUNT_APPLIED = new NamedFeature("DISCOUNT_APPLIED");
	
	@Autowired
	private InventoryService service;

	@GetMapping("/orders")
	public List<Product> showAvailableProducts() {
		
		if (manager.isActive(DISCOUNT_APPLIED)) {
			return applyDiscount(service.getAllProducts());
		} else {
			return service.getAllProducts();
		}
	}
	
	private List<Product> applyDiscount(List<Product> availableProducts) {
		List<Product> orderListAfterDiscount = new ArrayList<>();
		
		service.getAllProducts().forEach( order -> {
			
			order.setPrice(order.getPrice() - (order.getPrice() * 5/100));
			
			orderListAfterDiscount.add(order);
		});
		
		return orderListAfterDiscount;
	}

}
