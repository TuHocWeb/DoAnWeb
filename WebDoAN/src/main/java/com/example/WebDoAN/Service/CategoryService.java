package com.example.WebDoAN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.CategoryRepo;
import com.example.WebDoAN.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
	}
	public Category findById(Integer id)
	{
		return categoryRepo.getById(id);
	}
}
