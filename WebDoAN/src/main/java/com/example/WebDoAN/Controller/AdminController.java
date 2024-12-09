package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.ProductService;

@Controller
public class AdminController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/admin")
	private String AdminHome()
	{
		return "Admin";
	}
	
	
	@GetMapping("/danhmuc")
	private String ManagerCategory(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		return "ManagerCategory";
	}
}
