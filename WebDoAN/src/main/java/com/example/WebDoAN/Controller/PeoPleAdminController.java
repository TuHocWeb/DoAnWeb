package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.WebDoAN.Service.UserService;
import com.example.WebDoAN.entity.User;

@Controller
public class PeoPleAdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/nguoidung")
	private String ManagerCategory(Model model)
	{
		model.addAttribute("users", userService.getAllUser());
		return "ManagerPeople";
	}
	@GetMapping("/deletePeople/{id}")
	public String xoaNguoiDung(@PathVariable("id") Integer id)
	{
		User user=userService.findById(id);
	    userService.deletePeople(user);	
	    return "redirect:/nguoidung";
	}
	
	
}
