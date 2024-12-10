package com.example.WebDoAN.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.entity.Category;
import com.example.WebDoAN.entity.Product;

@Controller
public class CategoryAdminController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/addCategory")
	public String addcategory(@RequestParam("name")String tendanhmuc)
	{
		Category category=new Category();
		category.setName(tendanhmuc);
		category.setProducts(new ArrayList<Product>());
		categoryService.addCategory(category);
		return "redirect:/danhmuc";
	}
	@GetMapping("/deleteCategory/{id}")
	 public String deleteCategory(@PathVariable("id") Integer id) {
        if (id != null) {
        	Category category=categoryService.findById(id);
            categoryService.deleteCategory(category);
        }
        return "redirect:/danhmuc";
    }
	@GetMapping("/editCategory/{id}")
	public String OpenEditCategory(@PathVariable("id") Integer id,Model model)
	{
			Category category=categoryService.findById(id);
			model.addAttribute("category", category);
			return "EditDM";
	}
	@PostMapping("/editdm")
	public String EditCategory(@RequestParam("id")Integer id,@RequestParam("name")String name)
	{
		Category category=categoryService.findById(id);
		category.setName(name);
		categoryService.updateCategory(category);
        return "redirect:/danhmuc";
	}
	
}
