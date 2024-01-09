package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.dto.PurchaseDto;

public class PaymentCompleteDao {
	
	//Field
		private final String url_mysql = ShareVar.dbName;
		private final String id_mysql = ShareVar.dbUser;
		private final String pw_mysql = ShareVar.dbPass;
		
		
		String image; 
		int sellprice;
		int purseq; 
		String custid; 
		String proname; 
		int purqty; 
		String purdate; 
		String category; 
		int point; 
		int spendpoints;
		
		
		int orderseq; 
		String payment; 
		int payprice;	
		int accupoints; 
		String orderdate;
		




		//Method
		



		// 내역을 결제완료 창으로 불러오자. 
		public ArrayList<PurchaseDto> selectList() {
			ArrayList<PurchaseDto> dtoList = new ArrayList<PurchaseDto>(); 
			String whereDefault = "SELECT pr.image, pr.proname, pr.sellprice, p.purqty FROM purchase p, product pr WHERE pr.proname = p.proname ";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(whereDefault);
				
				
				while(rs.next()) {
					String image = rs.getString(1); 
					String proname = rs.getString(2);
					int sellprice = rs.getInt(3);
					int purqty = rs.getInt(4);
					
					
					PurchaseDto purchaseDto =  new PurchaseDto(image, proname, sellprice, purqty);
					dtoList.add(purchaseDto); 
							
				}
				conn_mysql.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return dtoList; 
			
			
		}
		

}
