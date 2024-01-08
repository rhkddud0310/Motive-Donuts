package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.dto.CartDto;

//Field

public class CartDao {
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
	
	

	public CartDao() {
		// TODO Auto-generated constructor stub
	}
	

	
	//Constructor
	
	
	public CartDao(String image, String proname, int sellprice, int purqty) {
		super();
		this.image = image;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}
	
	
	

	
	//Method
	
	public ArrayList<CartDto> selectList() {
		ArrayList<CartDto> dtoList = new ArrayList<CartDto>(); 
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
				
				
				CartDto cartDto =  new CartDto(image, proname, sellprice, purqty);
				dtoList.add(cartDto); 
						
			}
			conn_mysql.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList; 
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
