package com.example.WebDoAN.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.OrderDeatilRepo;
import com.example.WebDoAN.entity.OrderDetails;
import com.example.WebDoAN.entity.Product;

@Service
public class OrderDetalsService {

	@Autowired
	private OrderDeatilRepo orderDeatilRepo;
	
	@Autowired
	private ProductService productService;
	
	
	public List<OrderDetails> getAllOrderDetail()
	{
		return orderDeatilRepo.findAll();
	}
	public List<Product> bestSell()
	{
		List<Product> kq=new ArrayList<Product>();
		List<Integer> ds =orderDeatilRepo.get3BestSellingProduct();
		for (Integer integer : ds) {
			kq.add(productService.findProductByid(integer));
		}
		return kq;
	}
	

}
