package com.example.WebDoAN.entity;


public class CartItem {

	private Product product;
	private int quality;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public CartItem()
	{
		
	}
}
