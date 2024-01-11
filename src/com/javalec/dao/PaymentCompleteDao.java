package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.dto.PaymentCompleteDto;

public class PaymentCompleteDao {
	
	//Field
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
		
		
		
	
		
		
		//Constructor
		
		

		

		
		public PaymentCompleteDao(String proname, int sellprice, int purqty, String purdate) {
			super();
			this.proname = proname;
			this.sellprice = sellprice;
			this.purqty = purqty;
			this.purdate = purdate;
		}
		
		
		

		
		
	
		public PaymentCompleteDao(int payprice) {
			super();
			this.payprice = payprice;
		}
		





		public PaymentCompleteDao(String payment) {
			super();
			this.payment = payment;
		}


		//Method
	


		// 결제내역을 결제완료 창으로 불러오자. 


		public ArrayList<PaymentCompleteDto> selectList() {
			ArrayList<PaymentCompleteDto> dtoList = new ArrayList<PaymentCompleteDto>(); 
			String where = "SELECT p.proname, p.purqty, pr.sellprice, p.purdate FROM product pr, purchase p WHERE pr.proname = p.proname AND p.purdate = '2023-12-12' ";

			try {
 				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where);
				
				
				while(rs.next()) {
				
					
					String proname = rs.getString(1);	
					int sellprice = rs.getInt(2); 
					int purqty = rs.getInt(3); 
					String purdate = rs.getString(4); 
					
		
							
					PaymentCompleteDto paymentCompleteDto =  new PaymentCompleteDto (proname, sellprice, purqty, purdate);
					dtoList.add(paymentCompleteDto); 
							
				}
				conn_mysql.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return dtoList; 				
		}


		//PURCHASE TABLE DATA 결제금액  불러오기 
		
		

		public int totalPrice() {
			int totalPrice=0; 
			String where = "select payprice from myorder where custid = 'jojo' ";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where); 
				if(rs.next()) {
					totalPrice = rs.getInt(1);
				}
				conn_mysql.close();
		
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			return totalPrice; 						
		}
		
		
		//PURCHASE TABLE DATA 결제수단  불러오기 
		

		public String myPayment() {
			String payment=""; 
			String where = "select payment from myorder where custid = 'jojo' ";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where); 
				if(rs.next()) {
					payment = rs.getString(1);
				}
				conn_mysql.close();
		
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			return  payment; 						
		}
		
		
		
		//PURCHASE TABLE DATA  사용한 포인트 불러오기 
		
		public int myGetSpentPoints() {
			int getSetPoints =0; 
	
			String where = "SELECT m.spendpoints,  p.purdate FROM myorder m, purchase p WHERE m.proname = p.proname AND p.purdate = '2023-12-12' ";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where); 
				if(rs.next()) {
					getSetPoints = rs.getInt(1);
				}
				conn_mysql.close();
		
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			return  getSetPoints; 						
		}
		
		//PURCHASE TABLE DATA  받은 포인트 불러오기 
		
		public int receivedPoints() {
			int getRecievedPoints =0; 
	
			String where = "SELECT m.accupoints,  p.purdate FROM myorder m, purchase p WHERE m.proname = p.proname AND p.purdate = '2023-12-12' ";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where); 
				if(rs.next()) {
					getRecievedPoints = rs.getInt(1);
				}
				conn_mysql.close();
		
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			return  getRecievedPoints; 						
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
