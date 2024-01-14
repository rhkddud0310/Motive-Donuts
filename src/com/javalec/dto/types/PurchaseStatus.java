package com.javalec.dto.types;

public enum PurchaseStatus {
	ON_CART(100),
	COOKING(200);
	// ...
	
	private final int statusCode;
	
	PurchaseStatus(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int statusCode() {
		return statusCode;
	}
}
