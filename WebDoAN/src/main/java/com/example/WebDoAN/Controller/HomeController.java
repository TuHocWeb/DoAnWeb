package com.example.WebDoAN.Controller;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.entity.Cart;
import com.example.WebDoAN.entity.CartItem;
import com.example.WebDoAN.entity.Product;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/","/home"})
	public String Home(HttpSession session,Model model)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			model.addAttribute("userpeople",user);
		}
		model.addAttribute("categorys", categoryService.getAllCategory());
		return "TrangChu";
	}
	@GetMapping("/login")
	public String DangNhap()
	{
		return "DangNhap";
	}
	@GetMapping("/logout")
	public String ThoatDangNhap(HttpSession session)
	{
		session.removeAttribute("user");
		session.removeAttribute("cart");
		return "redirect:/home";
	}
	@GetMapping("/categoryId/{id}")
	public String categoryById(@PathVariable("id") Integer id,Model model,HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			model.addAttribute("userpeople",user);
		}
		List<Product> products=categoryService.dsbyCategoryID(id);
		model.addAttribute("products", products);
		model.addAttribute("categorys", categoryService.getAllCategory());
		model.addAttribute("categorychon", categoryService.findById(id));
		return "DanhSachMon";
	}
	@GetMapping("/viewCart")
	public String ViewCart(HttpSession session,Model model)
	{
		Cart cart=(Cart)session.getAttribute("cart");
		if(cart!=null)
		{
			model.addAttribute("cartitems", cart.getAllItem());
	        model.addAttribute("totalPrice", cart.totalMoney());
		}
		else
		{
        model.addAttribute("message", "Giỏ hàng hiện tại trống!");
		}
		return "GioHang";
	}
	@GetMapping("/addCartItem/{id}")
	public String addCartItem(@PathVariable("id") Integer id,HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user==null)
		{
			return "redirect:/login";
		}
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null)
		{
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		
		Product product=productService.findProductByid(id);
		CartItem cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuality(1);
		cart.addCartItem(cartItem);
		return "redirect:/viewCart";
	}
	@PostMapping("/update-cart")
	public String UpdateCat(HttpSession session,@RequestParam("id") Integer id,@RequestParam("quality")String sl,RedirectAttributes model)
	{
		try
		{
	        Integer quantity = Integer.parseInt(sl); 
			if(quantity<1)
			{
				model.addFlashAttribute("error", "Số lượng phải lớn hơn hoặc bằng 1");
				return "redirect:/viewCart";
			}
		Cart cart=(Cart)session.getAttribute("cart");
		cart.update(id, quantity);
		return "redirect:/viewCart";
		}
		catch (NumberFormatException e) {
			model.addFlashAttribute("error", "Bạn nhập không phải số");
			return "redirect:/viewCart";
		}
	}
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id") Integer id,HttpSession session)
	{
		Cart cart=(Cart)session.getAttribute("cart");
		cart.remove(id);
		return "redirect:/viewCart";
	}
	@GetMapping("/backbuy")
	public String BackBuy()
	{
		return "redirect:/home";
	}
	
}
