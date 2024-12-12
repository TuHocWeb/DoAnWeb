package com.example.WebDoAN.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.OrderDeatilRepo;
import com.example.WebDoAN.entity.OrderDetails;

@Service
public class OrderDetalsService {

	@Autowired
	private OrderDeatilRepo orderDeatilRepo;
	
	
	public List<OrderDetails> getAllOrderDetail()
	{
		return orderDeatilRepo.findAll();
	}
	

}
