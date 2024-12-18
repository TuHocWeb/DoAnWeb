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
	public double tinhGia()
	{
		return product.getPrice()*this.quality;
	}
	  public String getFormattedPrice() {
	        return MoneyUtil.formatCurrency(this.tinhGia()); // Sử dụng hàm static
	   }

}
