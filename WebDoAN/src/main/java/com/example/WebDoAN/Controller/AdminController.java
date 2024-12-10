package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/admin")
	private String AdminHome(HttpSession session,Model model)
	{
		 User currentUser = (User) session.getAttribute("currentuser");
		    if (currentUser != null) {
		        model.addAttribute("currentuser", currentUser);  
		        return "Admin";
		    } else {
		        return "redirect:/login";
		    }
	}
	@GetMapping("/backadmin")
	private String backAdmin()
	{
		
		 return "redirect:/admin";
	}
	
	@GetMapping("/danhmuc")
	private String ManagerCategory(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		return "ManagerCategory";
	}
	@GetMapping("/monan")
	private String ManagerProduct(Model model)
	{
		model.addAttribute("categorys", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		return "ManagerProduct";
	}
}
