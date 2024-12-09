package com.example.WebDoAN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.CategoryRepo;
import com.example.WebDoAN.Repo.ProductRepo;
import com.example.WebDoAN.entity.Category;
import com.example.WebDoAN.entity.Product;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	public List<Product> getAllProduct()
	{
		return productRepo.findAll();
	}
	public void addProduct(Product product,Integer category_id)
	{
		Category category=categoryRepo.findCategoryById(category_id);
		if(category!=null)
		{
			product.setCategory(category);
			productRepo.save(product);
		}
        throw new EntityNotFoundException("Category với  " + category_id + " khôn tìm thấy");
	
	}
	  public void removeProduct(Integer productId)
      {
      	Product product=productRepo.getById(productId);
      	productRepo.delete(product);
      }
	  public void UpdateProduct(Product product)
	  {
			productRepo.save(product);
	  }
	
	
}
