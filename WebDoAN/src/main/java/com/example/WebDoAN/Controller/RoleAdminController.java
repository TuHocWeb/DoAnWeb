package com.example.WebDoAN.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebDoAN.Service.RoleService;
import com.example.WebDoAN.entity.Role;
import com.example.WebDoAN.entity.User;


@Controller
public class RoleAdminController {

	@Autowired
	private RoleService roleService;
	
	
	
	
	@PostMapping("/addRole")
	public String addcategory(@RequestParam("id")String id,@RequestParam("name")String name,@RequestParam("description")String mota)
	{
		Role role=new Role();
		role.setId(Integer.parseInt(id));
		role.setName(name);
		role.setDescription(mota);
		role.setUsers(new ArrayList<User>());
		roleService.addrole(role);
		return "redirect:/chucvu";
	}
	@GetMapping("/deleteRole/{id}")
	 public String deleteCategory(@PathVariable("id") Integer id) {
       if (id != null) {
       	Role role=roleService.findById(id);
           roleService.deleteRole(role);
       }
       return "redirect:/chucvu";
   }
	@GetMapping("/editRole/{id}")
	public String OpenEditRole(@PathVariable("id") Integer id,Model model)
	{
			Role role=roleService.findById(id);
			model.addAttribute("role", role);
			return "EditRole";
	}
	@PostMapping("/editcv")
	public String EditCategory(@RequestParam("id")Integer id,@RequestParam("name")String name,@RequestParam("description")String mota)
	{
		Role role=roleService.findById(id);
		role.setName(name);
		role.setId(id);
		role.setDescription(mota);
		roleService.addrole(role);
        return "redirect:/chucvu";
	}
}
