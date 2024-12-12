package com.example.WebDoAN.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebDoAN.entity.OrderDetails;
import com.example.WebDoAN.entity.Orders;

public interface OrderDeatilRepo extends JpaRepository<OrderDetails, Integer> {

	  public List<OrderDetails> findByOrders(Orders orders);

	  public List<OrderDetails> findByOrdersId(Integer orderId);
}
