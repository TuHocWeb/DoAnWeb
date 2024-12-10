package com.example.WebDoAN.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.entity.Product;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/","/home"})
	public String Home(HttpSession session,Model model)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			model.addAttribute("userpeople",user);
		}
		model.addAttribute("categorys", categoryService.getAllCategory());
		return "TrangChu";
	}
	@GetMapping("/login")
	public String DangNhap()
	{
		return "DangNhap";
	}
	@GetMapping("/logout")
	public String ThoatDangNhap(HttpSession session)
	{
		session.removeAttribute("user");
		return "redirect:/home";
	}
	@GetMapping("/categoryId/{id}")
	public String categoryById(@PathVariable("id") Integer id,Model model,HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			model.addAttribute("userpeople",user);
		}
		List<Product> products=categoryService.dsbyCategoryID(id);
		model.addAttribute("products", products);
		model.addAttribute("categorys", categoryService.getAllCategory());
		model.addAttribute("categorychon", categoryService.findById(id));
		return "DanhSachMon";
	}
	
}
