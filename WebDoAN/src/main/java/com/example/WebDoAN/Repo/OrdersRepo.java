package com.example.WebDoAN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer>  {

}
