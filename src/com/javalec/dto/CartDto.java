package com.javalec.dto;

public class CartDto {
	
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
	
	public CartDto(String image, String proname, int sellprice, int purqty) {
		super();
		this.image = image;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}

	
	
	public CartDto(String image, int purseq, String custid, String proname, int purqty, String purdate,
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

	
	
	
	public CartDto(String proname) {
		super();
		this.proname = proname;
	}
	
	
	
	
	
	public CartDto(int purseq, String custid, String proname, int purqty, String purdate, String category) {
		super();
		this.purseq = purseq;
		this.custid = custid;
		this.proname = proname;
		this.purqty = purqty;
		this.purdate = purdate;
		this.category = category;
	}
	
	
	
	
	
	
	
	//Setters & Getters




	public String getImage() {
		return image;
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



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}


	

	
	
	
	
	
	
	
	

}
