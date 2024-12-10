package com.example.WebDoAN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.CategoryRepo;
import com.example.WebDoAN.entity.Category;
import com.example.WebDoAN.entity.Product;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
	}
	public List<Product> dsbyCategoryID(Integer categoryId)
	{
		Category category=categoryRepo.findCategoryById(categoryId);
		return category.getProducts();
	}
	public Category findById(Integer id)
	{
		return categoryRepo.getById(id);
	}
	public void addCategory(Category category)
	{
		Category category2=categoryRepo.findByName(category.getName());
		if(category2!=null)
		{
			return;
		}
		categoryRepo.save(category);
	}
	public void deleteCategory(Category category)
	{
		categoryRepo.delete(category);
	}
	public void updateCategory(Category category)
	{
		categoryRepo.save(category);
	}
}
