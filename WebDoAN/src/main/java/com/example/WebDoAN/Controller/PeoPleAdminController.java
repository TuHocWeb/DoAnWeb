package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebDoAN.Service.RoleService;
import com.example.WebDoAN.Service.UserService;
import com.example.WebDoAN.entity.Category;
import com.example.WebDoAN.entity.Product;
import com.example.WebDoAN.entity.Role;
import com.example.WebDoAN.entity.User;

@Controller
public class PeoPleAdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/nguoidung")
	private String ManagerCategory(Model model)
	{
		model.addAttribute("roles", roleService.getAllRole());
		model.addAttribute("users", userService.getAllUser());
		return "ManagerPeople";
	}
	@PostMapping("/addPeople")
	public String addPeople(@RequestParam("name")String ten,@RequestParam("username")String username,@RequestParam("password")String pass,@RequestParam("role")Integer id)
	{
		User user=new User();
		user.setFullname(ten);
		user.setUsername(username);
		user.setEnabled(true);
		user.setPassword(pass);
		Role role=roleService.findById(id);
		user.setRole(role);
		userService.CreatUsẻ(user);
		return "redirect:/nguoidung";
	}
	
	@GetMapping("/deletePeople/{id}")
	public String xoaNguoiDung(@PathVariable("id") Integer id)
	{
		User user=userService.findById(id);
	    userService.deletePeople(user);	
	    return "redirect:/nguoidung";
	}
	
	
}
