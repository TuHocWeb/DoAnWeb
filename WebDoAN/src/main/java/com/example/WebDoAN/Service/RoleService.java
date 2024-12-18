package com.example.WebDoAN.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.RoleRepo;
import com.example.WebDoAN.entity.Role;

@Service
public class RoleService {

	
	@Autowired
	private RoleRepo roleRepo;
	
	
	
	public List<Role> getAllRole()
	{
		return roleRepo.findAll();
	}
	public void addrole(Role role)
	{
		roleRepo.save(role);
	}
	public void deleteRole(Role role)
	{
		roleRepo.delete(role);
	}
	public Role findById(Integer id)
	{
		return roleRepo.getById(id);
	}
	
	public Role findByUsernames(String username)
	{
		return roleRepo.findByName(username);
	}
	
	
}
