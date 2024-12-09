package com.example.WebDoAN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
  public Category findCategoryById(Integer id);
}
