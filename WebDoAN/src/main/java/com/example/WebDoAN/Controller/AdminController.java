package com.example.WebDoAN.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.OrderDetalsService;
import com.example.WebDoAN.Service.OrdersService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.Service.RoleService;
import com.example.WebDoAN.entity.OrderDetails;
import com.example.WebDoAN.entity.OrderStatus;
import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderDetalsService orderDetalsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private RoleService roleService;
	
	
	@GetMapping("/admin")
	private String AdminHome(HttpSession session,Model model)
	{
		 User currentUser = (User) session.getAttribute("currentuser");
		    if (currentUser != null) {
		        model.addAttribute("currentuser", currentUser);  
		        return "Admin";
		    } else {
		        return "redirect:/login";
		    }
	}
	@GetMapping("/backadmin")
	private String backAdmin()
	{
		
		 return "redirect:/admin";
	}
	
	@GetMapping("/danhmuc")
	private String ManagerCategory(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		return "ManagerCategory";
	}
	@GetMapping("/donhang")
	private String ManagerDonHang(Model model)
	{
		model.addAttribute("orders", ordersService.getAllOrder());
		return "ManagerHoaDon";

	}
	@GetMapping("/order/details/{orderId}")
	public String chitiethoaodn(@PathVariable("orderId")Integer id,Model model)
	{
		Orders orders=ordersService.findById(id);
		List<OrderDetails> list=orders.getOrderDetails();
		model.addAttribute("orders", list);
		model.addAttribute("order", orders);
		return "chitiethoadon";
	}
	@GetMapping("/chucvu")
	public String ChucVu(Model model)
	{
		model.addAttribute("roles", roleService.getAllRole());
		return "ManagerRole";
	}
	@GetMapping("/backdanhsach")
	public String backdanhsach()
	{
		return "redirect:/donhang";
	}
	@PostMapping("/order/updateStatus")
	public String updateSatus(@RequestParam("orderId") Integer id,@RequestParam("status") String status)
	{
		  Orders orders=ordersService.findById(id);
	      OrderStatus orderStatus = OrderStatus.valueOf(status);
          orders.setStatus(orderStatus);
          ordersService.cretateoreder(orders);
          return "redirect:/donhang";
	}
	@GetMapping("/monan")
	private String ManagerProduct(Model model)
	{
		model.addAttribute("categorys", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		return "ManagerProduct";
	}
}
