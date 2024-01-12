package com.javalec.dto;

import java.io.FileInputStream;

public class PurchaseDto {
	
   //Field 
	
	
	int purseq; 
	String custid; 
	String proname; 
	int purqty; 
	String purdate; 
	String category;
	int sellprice; 
	int point;
	String imagename; 
	
	int orderseq; 
	String payment; 
	int payprice;	
	int accupoints; 
	String orderdate;
	int spendpoints;
	

	
	//Constructor
	


	public PurchaseDto(String custid, String proname, int orderseq, String payment, int payprice, int accupoints,
			String orderdate, int spendpoints) {
		super();
		this.custid = custid;
		this.proname = proname;
		this.orderseq = orderseq;
		this.payment = payment;
		this.payprice = payprice;
		this.accupoints = accupoints;
		this.orderdate = orderdate;
		this.spendpoints = spendpoints;
	}



	public PurchaseDto(String imagename, String proname, int sellprice, int purqty) {
		super();
		this.imagename = imagename;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}



	public PurchaseDto(int point) {
		super();
		this.point = point;
	}



	public PurchaseDto(String imagename, int purseq, String custid, String proname, int purqty, String purdate,
			String category, int sellprice, int point) {
		super();
		this.imagename = imagename;
		this.purseq = purseq;
		this.custid = custid;
		this.proname = proname;
		this.purqty = purqty;
		this.purdate = purdate;
		this.category = category;
		this.sellprice = sellprice;
		this.point = point;
	}


	
	public PurchaseDto(int purseq, String imagename, String proname, int sellprice, int purqty) {
		super();
		this.purseq = purseq; 
		this.imagename = imagename;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}
	
	
	
	
	
	
	
	
	
	
	//SETTERS GETTERS
	
	
	

	

	public String getImagename() {
		return imagename;
	}



	public int getOrderseq() {
		return orderseq;
	}



	public void setOrderseq(int orderseq) {
		this.orderseq = orderseq;
	}



	public String getPayment() {
		return payment;
	}



	public void setPayment(String payment) {
		this.payment = payment;
	}



	public int getPayprice() {
		return payprice;
	}



	public void setPayprice(int payprice) {
		this.payprice = payprice;
	}



	public int getAccupoints() {
		return accupoints;
	}



	public void setAccupoints(int accupoints) {
		this.accupoints = accupoints;
	}



	public String getOrderdate() {
		return orderdate;
	}



	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}



	public int getSpendpoints() {
		return spendpoints;
	}



	public void setSpendpoints(int spendpoints) {
		this.spendpoints = spendpoints;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}



	public void setImage(String imagename) {
		this.imagename = imagename;
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

