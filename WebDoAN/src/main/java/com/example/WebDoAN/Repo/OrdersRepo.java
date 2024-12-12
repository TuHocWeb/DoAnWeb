package com.example.WebDoAN.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.User;

public interface OrdersRepo extends JpaRepository<Orders, Integer>  {

    public List<Orders> findByUser(User user);
}
