package com.example.WebDoAN.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.naming.InsufficientResourcesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebDoAN.Repo.OrdersRepo;
import com.example.WebDoAN.Repo.ProductRepo;
import com.example.WebDoAN.entity.Cart;
import com.example.WebDoAN.entity.CartItem;
import com.example.WebDoAN.entity.OrderDetails;
import com.example.WebDoAN.entity.OrderStatus;
import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.Product;
import com.example.WebDoAN.entity.User;

import jakarta.transaction.Transactional;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private ProductRepo productRepo;
	
	public List<Orders> getAllOrder()
	{
		return ordersRepo.findAll();
	}
	public Orders findById(Integer id)
	{
		try
		{
			return ordersRepo.getById(id);
		}
		catch (Exception e) {
			return null;
		}
	}
	public Orders findOrderbyid(Integer id)
	{
		try
		{
		return ordersRepo.getById(id);
		}
		catch(Exception e){
			return null;
		}
	}
	public void cretateoreder(Orders orders)
	{
		ordersRepo.save(orders);
	}
	public List<Orders> findByuser(User user)
	{
		return ordersRepo.findByUser(user);
	}
	@Transactional
	public Orders addOrders(String hoten, String diachi, String sdt, User user, Cart cart) {
	    Orders orders = new Orders();
	    orders.setFullname(hoten);
	    orders.setAddress(diachi);
	    orders.setPhone_number(sdt);
	    orders.setStatus(OrderStatus.PENDING);
	    orders.setUser(user);
	    orders.setOrder_date(Date.valueOf(LocalDate.now()));
	    orders.setTotal_money(cart.totalMoney());

	    Collection<CartItem> cartItems = cart.getAllItem();

	    try {
	        for (CartItem cartItem : cartItems) {
	            Product product = productRepo.getById(cartItem.getProduct().getId());
	            
	            if (product.getStock() < cartItem.getQuality()) {
	                throw new InsufficientResourcesException("Sản phẩm " + product.getTitle() + " không đủ trong kho.");
	            }

	            product.setStock(product.getStock() - cartItem.getQuality());
	            OrderDetails orderDetails = new OrderDetails();
	            orderDetails.setProdut(product);
	            orderDetails.setPrice(product.getPrice());
	            orderDetails.setNum(cartItem.getQuality());
	            orderDetails.setTotal_money(cartItem.getQuality() * product.getPrice());
	            orderDetails.setOrders(orders);
	            orders.getOrderDetails().add(orderDetails);
	            productRepo.save(product);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Lỗi khi xử lý đơn hàng: " + e.getMessage(), e);
	    }
	    ordersRepo.save(orders);
	    return orders;
	}

}
