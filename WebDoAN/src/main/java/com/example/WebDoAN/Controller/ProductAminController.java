package com.example.WebDoAN.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebDoAN.Service.CategoryService;
import com.example.WebDoAN.Service.ProductService;
import com.example.WebDoAN.entity.Category;
import com.example.WebDoAN.entity.Product;

@Controller
public class ProductAminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam("title")String tenmonan,@RequestParam("image")String hinhanh,@RequestParam("price")String gia,@RequestParam("stock")String soluong,@RequestParam("description")String mota,@RequestParam("category")Integer id)
	{
		Product product=new Product();
		product.setTitle(tenmonan);
		product.setThumbnail(hinhanh);
		product.setPrice(Double.parseDouble(gia));
		product.setStock(Integer.parseInt(soluong));
		product.setDescription(mota);
		Category category=categoryService.findById(id);
		productService.addProduct(product, category.getId());
		return "redirect:/monan";
	}
	
	@GetMapping("/deleteProduct/{id}")
	 public String deleteProduct(@PathVariable("id") Integer id) {
       if (id != null) {
           productService.removeProduct(id);
       }
       return "redirect:/monan";
   }
	@GetMapping("/editProduct/{id}")
	public String OpenEditProduct(@PathVariable("id") Integer id,Model model)
	{
			model.addAttribute("categorys", categoryService.getAllCategory());
			Product product=productService.findProductByid(id);
			model.addAttribute("product", product);
			return "EditProduct";
	}
	@PostMapping("/editPro")
	public String editProduct(@RequestParam("id")Integer id,@RequestParam("title")String tenmonan,@RequestParam("thumbnail")String hinhanh,@RequestParam("price")String gia,@RequestParam("stock")String soluong,@RequestParam("description")String mota,@RequestParam("category")Integer idcategory)
	{

		Product product=productService.findProductByid(id);
		product.setTitle(tenmonan);
		product.setThumbnail(hinhanh);
		product.setPrice(Double.parseDouble(gia));
		product.setStock(Integer.parseInt(soluong));
		product.setDescription(mota);
		Category category=categoryService.findById(idcategory);
		product.setCategory(category);
		productService.UpdateProduct(product);
		return "redirect:/monan";
	}
}
