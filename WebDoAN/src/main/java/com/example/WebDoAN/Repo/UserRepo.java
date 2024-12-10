package com.example.WebDoAN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
