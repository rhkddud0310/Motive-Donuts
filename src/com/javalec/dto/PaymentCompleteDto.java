package com.javalec.dto;

import com.javalec.common.ShareVar;

public class PaymentCompleteDto {
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	int orderseq; 
	String custid; 

	String payment; 
	int payprice; 
	int spendpoints; 
	int accupoints; 
	String orderdate;
	
	
	String proname; 
	int sellprice;
	int purqty;
	String purdate; 
	
	
	
	
//CONSTRUCTOR	
	
	
	public PaymentCompleteDto(int orderseq, String custid, String proname, String payment, int payprice,
			int spendpoints, int accupoints, String orderdate) {
		super();
		this.orderseq = orderseq;
		this.custid = custid;
		this.proname = proname;
		this.payment = payment;
		this.payprice = payprice;
		this.spendpoints = spendpoints;
		this.accupoints = accupoints;
		this.orderdate = orderdate;
	}
	
	

	public PaymentCompleteDto(String payment) {
		super();
		this.payment = payment;
	}




	public PaymentCompleteDto(String proname, int sellprice, int purqty, String purdate) {
		super();
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
		this.purdate = purdate;
	}




	//SETTERS&GETTERS





	public int getOrderseq() {
		return orderseq;
	}
	public int getSellprice() {
		return sellprice;
	}




	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
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




	public void setOrderseq(int orderseq) {
		this.orderseq = orderseq;
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
	public int getSpendpoints() {
		return spendpoints;
	}
	public void setSpendpoints(int spendpoints) {
		this.spendpoints = spendpoints;
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
}
	