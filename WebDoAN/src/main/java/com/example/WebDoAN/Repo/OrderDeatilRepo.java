package com.example.WebDoAN.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WebDoAN.entity.OrderDetails;
import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.Product;

public interface OrderDeatilRepo extends JpaRepository<OrderDetails, Integer> {

	  public List<OrderDetails> findByOrders(Orders orders);

	  public List<OrderDetails> findByOrdersId(Integer orderId);
	  
	  @Query(value = "SELECT product_id " +
              "FROM order_details " +
              "GROUP BY product_id " +
              "ORDER BY SUM(num) DESC " +
              "LIMIT 3", 
              nativeQuery = true)
	  	public List<Integer> get3BestSellingProduct();

}
