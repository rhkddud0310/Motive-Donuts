package com.javalec.dto;

import java.sql.Timestamp;

public class MyOrderListDto {

	//Filed
	int orderseq;
	String custid;
	String proname;
	String payment;
	int payprice;
	int spendpoints;
	int accupoints;
	Timestamp orderdate;
	
	// Constructor
	public MyOrderListDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MyOrderListDto(String proname, String payment, int payprice, int spendpoints, int accupoints,
			Timestamp orderdate) {
		super();
		this.proname = proname;
		this.payment = payment;
		this.payprice = payprice;
		this.spendpoints = spendpoints;
		this.accupoints = accupoints;
		this.orderdate = orderdate;
	}


	public MyOrderListDto(String custid, int accupoints, int spendpoints) {
		super();
		this.custid = custid;
		this.accupoints = accupoints;
		this.spendpoints = spendpoints;
	}


	public MyOrderListDto(int orderseq, String custid, String proname, String payment, int payprice, int spendpoints,
			int accupoints, Timestamp orderdate) {
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

	public int getOrderseq() {
		return orderseq;
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

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}
	
	
}
