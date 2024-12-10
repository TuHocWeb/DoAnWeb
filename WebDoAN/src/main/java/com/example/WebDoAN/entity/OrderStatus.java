package com.example.WebDoAN.entity;

public enum OrderStatus {
	    PENDING,        // Chờ xử lý
	    PROCESSING,     // Đang xử lý
	    SHIPPED,        // Đã giao hàng
	    DELIVERED,      // Đã nhận hàng
	    CANCELED;       // Đã hủy

	    public String getStatusDescription() {
	        switch (this) {
	            case PENDING:
	                return "Chờ xử lý";
	            case PROCESSING:
	                return "Đang xử lý";
	            case SHIPPED:
	                return "Đã giao hàng";
	            case DELIVERED:
	                return "Đã nhận hàng";
	            case CANCELED:
	                return "Đã hủy";
	            default:
	                return "Chưa xác định";
	        }
	    }

}
