package com.example.WebDoAN.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> dsproduct=new HashMap<>();
		
	public void addCartItem(CartItem item)
	{
		CartItem caItem=dsproduct.get(item.getProduct().getId());
		if(caItem==null)
		{
			dsproduct.put(item.getProduct().getId(), item);
		}
		else
		{
			caItem.setQuality(caItem.getQuality()+1);
		}
	}
	public void remove(int id)
	{
		dsproduct.remove(id);
	}
	public CartItem update(int proID, int quality)
	{
		CartItem cartItem=dsproduct.get(proID);
		cartItem.setQuality(quality);
		return cartItem;
	}
	public Collection<CartItem> getAllItem()
	{
		return dsproduct.values();
	}
	public int getCount()
	{
		return dsproduct.size();
	}
	public double totalMoney()
	{
		return dsproduct.values().stream()
								.mapToDouble(item->item.getQuality()*item.getProduct().getPrice())
								.sum();
	}
	 public String getFormattedPrice() {
	        return MoneyUtil.formatCurrency(this.totalMoney()); // Sử dụng hàm static
	   }
	
}
