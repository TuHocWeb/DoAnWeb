package com.example.WebDoAN.Controller;

import java.text.DecimalFormat;
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
import com.example.WebDoAN.Service.OrderDetalsService;
import com.example.WebDoAN.Service.OrdersService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.Service.RoleService;
import com.example.WebDoAN.Service.UserService;
import com.example.WebDoAN.entity.Cart;
import com.example.WebDoAN.entity.CartItem;
import com.example.WebDoAN.entity.OrderStatus;
import com.example.WebDoAN.entity.Orders;
import com.example.WebDoAN.entity.Product;
import com.example.WebDoAN.entity.Role;
import com.example.WebDoAN.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrderDetalsService orderDetalsService;
	
	@GetMapping({"/","/home"})
	public String Home(HttpSession session,Model model)
	{
		User user=(User)session.getAttribute("user");
		Cart cart=(Cart)session.getAttribute("cart");
		if(user!=null)
		{
			model.addAttribute("userpeople",user);
		}
		if(cart!=null)
		{
			model.addAttribute("cartQuantity", cart.getCount());
		}
		model.addAttribute("categorys", categoryService.getAllCategory());
		model.addAttribute("best", orderDetalsService.bestSell());
		return "TrangChu";
	}
	@GetMapping("/login")
	public String DangNhap()
	{
		return "DangNhap";
	}
	@GetMapping("/resign")
	public String DangKy()
	{
		return "DangKy";
	}
	@PostMapping("/signup")
	public String DangKiTaiKhoan(@RequestParam("name")String ten,@RequestParam("email")String email,@RequestParam("password")String pass,@RequestParam("repasword")String repas,RedirectAttributes model)
	{
		User user=userService.findbyUsername(email);
		if(!pass.equals(repas))
		{
			model.addFlashAttribute("message", "Sai Thông tin mật khẩu");
			return "redirect:/resign";
		}
		if(user!=null)
		{
			model.addFlashAttribute("message", "Đã Tồn tại tài khoản vui lòng thử lại.");
			return "redirect:/resign";
		}
		User user2=new User();
		user2.setFullname(ten);
		user2.setUsername(email);
		user2.setPassword(pass);
		user2.setEnabled(true);
		Role role=roleService.findById(2);
		user2.setRole(role);
		userService.CreatUsẻ(user2);
		model.addFlashAttribute("message", "Đăng kí thành công bạn có thể đăng nhập.");
		return "redirect:/login";
		
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
		Cart cart=(Cart)session.getAttribute("cart");
		if(cart!=null)
		{
			model.addAttribute("cartQuantity", cart.getCount());
		}
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
	        model.addAttribute("totalPrice", cart.getFormattedPrice());
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
		Product product=productService.findProductByid(id);
		try
		{
	        Integer quantity = Integer.parseInt(sl); 
			if(quantity<1)
			{
				model.addFlashAttribute("error", "Số lượng phải lớn hơn hoặc bằng 1");
				return "redirect:/viewCart";
			}
			if(quantity>product.getStock())
			{
				model.addFlashAttribute("error", "Số lượng trong kho không đủ ");
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
	@GetMapping("/danhsachdon")
	public String DanhSachDon(HttpSession session,Model model)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			List<Orders> order=user.getOrders();
			model.addAttribute("orders", order);
			return "DanhSachDonHang";
		}
		model.addAttribute("message", "Đơn hàng của bạn đang trống");
		return "DanhSachDonHang";

	}
	@PostMapping("/order/cancel/{orderId}")
	public String huydanhsachdonhang(@PathVariable("orderId") Integer id,@RequestParam("cancellationReason")String lydohuy,HttpSession session)
	{
		Orders orders=ordersService.findById(id);
        orders.setStatus(OrderStatus.CANCELED);
        orders.setHuydonhang(lydohuy);
        ordersService.cretateoreder(orders);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Orders> orderList = ordersService.findByuser(user);
            user.setOrders(orderList);  
            session.setAttribute("user", user);
        }
        return "redirect:/danhsachdon";
	}
	public String formatCurrency(double amount) {
	    DecimalFormat df = new DecimalFormat("#,###");
	    return df.format(amount) + " VND";
	}

	
}
