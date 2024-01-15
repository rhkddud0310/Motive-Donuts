package com.javalec.dto.types;

public enum PurchaseGubun {
	ON_CART(100),
	COOKING(200);
	// ...
	
	private final int gubunCode;
	
	PurchaseGubun(int gubunCode) {
		this.gubunCode = gubunCode;
	}
	
	public int gubunCode() {
		return gubunCode;
	}
}
