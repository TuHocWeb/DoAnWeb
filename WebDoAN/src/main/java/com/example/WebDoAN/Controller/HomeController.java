package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebDoAN.Service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@GetMapping({"/","/home"})
	public String Home()
	{
		return "TrangChu";
	}
	@GetMapping("/login")
	public String DangNhap()
	{
		return "DangNhap";
	}
	
}
