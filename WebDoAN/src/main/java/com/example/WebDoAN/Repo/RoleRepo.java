package com.example.WebDoAN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	public Role findByName (String name);
}
