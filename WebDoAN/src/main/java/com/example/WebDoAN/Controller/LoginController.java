package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebDoAN.Service.UserService;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/verify")
	public String verifylogin(@RequestParam("username")String username,@RequestParam("password")String passowrd,RedirectAttributes model,HttpSession session)
	{
		User user=userService.findbyUsername(username);
		int check=userService.verifyLogin(username, passowrd);
		if(check==1)
		{
			session.setAttribute("currentuser", user);
			return "redirect:/admin";
		}
		else if(check==2)
		{
			 session.setAttribute("user", user);
		     return "redirect:/home"; 
		}
		else
		{
		model.addFlashAttribute("error", "Không tìm thấy tài khoản và mật khẩu");
		return "redirect:/login";
		}
	}
}
