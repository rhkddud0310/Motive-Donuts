package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.common.ShareVar;

public class MyOrderDao {
	
	//FIELD
	
		private final String url_mysql = ShareVar.dbName;
		private final String id_mysql = ShareVar.dbUser;
		private final String pw_mysql = ShareVar.dbPass;
		
		
		int orderseq; 
		String custid; 
		String proname; 
		String payment; 
		int payprice;	
		int spendpoints;
		int accupoints; 
		String orderdate;
		String image; 
		int sellprice;
		int purseq; 
		int purqty; 
		String purdate; 
		String category; 
		int point; 
	
	
	    public MyOrderDao() {
			// TODO Auto-generated constructor stub
		}
	
	    
	    
	    //CONSTRUCTOR
	    
	    
	    public MyOrderDao(int orderseq, String custid, String proname, String payment, int payprice, int spendpoints, int accupoints) {
	    	super();
	    	this.orderseq = orderseq;
	    	this.custid = custid;
	    	this.proname = proname;
	    	this.payment = payment;
	    	this.payprice = payprice;
	    	this.spendpoints = spendpoints;
	    	this.accupoints = accupoints;
	    }
		
	  

	    public MyOrderDao(int orderseq, String custid, String proname, String payment, int payprice, int spendpoints,
	    		int accupoints, String orderdate) {
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
	    
	    
	    
	    
	    //METHOD
	    
	    
		//결제하기 눌렀을 경우 orders table 데이터 값으로 넣어주자. 
		
		
	    public boolean ordersUpdate() {
			PreparedStatement ps = null;
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				String A = "insert into myorder (orderseq, custid, proname, payment, payprice, spendpoints, accupoints, orderdate";
				String B = " ) values (?,?,?,?,?,?,?,sysdate())";
				
				System.out.println(orderseq);
				System.out.println(custid);
				System.out.println(proname);
				System.out.println(payment);
				System.out.println(payprice);
				System.out.println(spendpoints);
				System.out.println(accupoints);
				
					
				ps = conn_mysql.prepareStatement(A+B);
				ps.setInt(1, orderseq);
				ps.setString(2, custid);
				ps.setString(3, proname);
				ps.setString(4, payment);
				ps.setInt(5, payprice);
				ps.setInt(6, spendpoints);
				ps.setInt(7, accupoints);

				
				ps.executeUpdate();
				
				conn_mysql.close();
				
			}catch(Exception e) {
				return false;
			}
			
			return true;	
		}



	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
