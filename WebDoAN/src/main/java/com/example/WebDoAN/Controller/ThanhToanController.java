package com.example.WebDoAN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebDoAN.Service.OrdersService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.entity.Cart;
import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ThanhToanController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("/checkout")
	public String ThanhToan(HttpSession session,Model model2,RedirectAttributes model)
	{
		Cart cart=(Cart)session.getAttribute("cart");
		User user=(User)session.getAttribute("user");
		if(cart!=null)
		{
			model2.addAttribute("cartitems", cart.getAllItem());
			model2.addAttribute("totalMoney", cart.totalMoney());
			return "ThanhToan";
		}
		else
		{
			model.addFlashAttribute("loi", "Bạn Chưa Chọn Sản Phẩm Nào!!");
			return "redirect:/viewCart";
		}
	}
	@PostMapping("/ThanhToanHoaDon")
	public String ThanhToanTinhTien(@RequestParam("fullname") String ten,@RequestParam("address") String diachi,@RequestParam("phone") String sdt,HttpSession session,Model model)
	{
		User user=(User)session.getAttribute("user");
		Cart cart=(Cart)session.getAttribute("cart");
		try
		{
		Orders orders=ordersService.addOrders(ten, diachi, sdt, user, cart);
		model.addAttribute("thanhcong", ordersService.findById(orders.getId()));
		return "thanhtoanthanhcong";
		}
		catch(Exception e)
		{
			return "thanhtoanthatbai";
		}
	}
}
