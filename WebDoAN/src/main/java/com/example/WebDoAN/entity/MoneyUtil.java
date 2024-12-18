package com.example.WebDoAN.entity;

import java.text.DecimalFormat;

public class MoneyUtil {
	  public static String formatCurrency(double amount) {
	        DecimalFormat decimalFormat = new DecimalFormat("#,###"); // Định dạng dấu phẩy
	        return decimalFormat.format(amount) + " VND"; // Thêm đơn vị "VND"
	    }

}
