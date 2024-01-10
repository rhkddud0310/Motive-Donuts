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
		String proname; 
		String payment; 
		int payprice; 
		int spendpoints; 
		int accupoints; 
		String orderdate; 
		
	
		
		
		//Constructor
		
		
		
		public PaymentCompleteDao(int orderseq, String custid, String proname, String payment, int payprice,
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
		
		
		



		//Method
	


		// 구매내역을 결제완료 창으로 불러오자. 
		public ArrayList<PaymentCompleteDto> selectList() {
			ArrayList<PaymentCompleteDto> dtoList = new ArrayList<PaymentCompleteDto>(); 
			String where = "SELECT orderseq,  custid,  proname,  payment,  payprice,  spendpoints,  accupoints,  orderdate FROM myorder ";

			try {
 				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where);
				
				
				while(rs.next()) {
				
					int orderseq = rs.getInt(1);
					String custid = rs.getString(2); 
					String proname = rs.getString(3);
					String payment = rs.getString(4);
					int payprice = rs.getInt(5); 
					int spendpoints = rs.getInt(6);
					int accupoints = rs.getInt(7);
					String orderdate = rs.getString(8);
		
							
					PaymentCompleteDto paymentCompleteDto =  new PaymentCompleteDto (orderseq,  custid,  proname,  payment,  payprice,  spendpoints,  accupoints,  orderdate );
					dtoList.add(paymentCompleteDto); 
							
				}
				conn_mysql.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return dtoList; 				
		}

}
