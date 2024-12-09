package com.example.WebDoAN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	
}
