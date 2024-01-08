package com.javalec.purchase;

import java.io.FileInputStream;

public class PurchaseDto {
	
   //Field 
	
	String image; 
	int purseq; 
	String custid; 
	String proname; 
	int purqty; 
	String purdate; 
	String category;
	int sellprice; 
	int point;
	

	
	//Constructor
	


	public PurchaseDto(String image, String proname, int sellprice, int purqty) {
		super();
		this.image = image;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}



	public PurchaseDto(int point) {
		super();
		this.point = point;
	}



	public PurchaseDto(String image, int purseq, String custid, String proname, int purqty, String purdate,
			String category, int sellprice, int point) {
		super();
		this.image = image;
		this.purseq = purseq;
		this.custid = custid;
		this.proname = proname;
		this.purqty = purqty;
		this.purdate = purdate;
		this.category = category;
		this.sellprice = sellprice;
		this.point = point;
	}


	
	
	
	//SETTERS GETTERS
	
	
	

	

	public String getImage() {
		return image;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public int getPurseq() {
		return purseq;
	}



	public void setPurseq(int purseq) {
		this.purseq = purseq;
	}



	public String getCustid() {
		return custid;
	}



	public void setCustid(String custid) {
		this.custid = custid;
	}



	public String getProname() {
		return proname;
	}



	public void setProname(String proname) {
		this.proname = proname;
	}



	public int getPurqty() {
		return purqty;
	}



	public void setPurqty(int purqty) {
		this.purqty = purqty;
	}



	public String getPurdate() {
		return purdate;
	}



	public void setPurdate(String purdate) {
		this.purdate = purdate;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public int getSellprice() {
		return sellprice;
	}



	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}











}

