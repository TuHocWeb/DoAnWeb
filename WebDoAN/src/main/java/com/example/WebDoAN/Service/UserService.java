package com.example.WebDoAN.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.RoleRepo;
import com.example.WebDoAN.Repo.UserRepo;
import com.example.WebDoAN.entity.Role;
import com.example.WebDoAN.entity.User;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	public List<User> getAllUser()
	{
		return userRepo.findAll();
	}
	public void deletePeople(User user)
	{
		userRepo.delete(user);
	}
	public void CreatUsẻ(User user)
	{
		userRepo.save(user);
	}
	public User findbyUsername(String username)
	{
		return userRepo.findByUsername(username);
	}
	public User findById(Integer id)
	{
		return userRepo.getById(id);
	}
	public int verifyLogin(String username,String passworld)
	{
		User user=findbyUsername(username);
		if(user==null)
		{
			return 0;
		}
	    Role role =user.getRole();
	    if (role == null) {
	        return 0;
	    }
	    if (!user.getPassword().equals(passworld)) {
	        return 0; 
	    }
		if(role.getName().equals("admin"))
		{
			return 1;
		}
		else if(role.getName().equals("user"))
		{
			return 2;
		}
		
		return 0;
	}
	

}
