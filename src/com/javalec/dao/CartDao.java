package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	
	
	
	public CartDao(String proname) {
		super();
		this.proname = proname;
	}

	
	
	
	public CartDao(int purseq, String custid, String proname, int purqty, String purdate, String category) {
		super();
		this.purseq = purseq;
		this.custid = custid;
		this.proname = proname;
		this.purqty = purqty;
		this.purdate = purdate;
		this.category = category;
	}
	
	
	
	
	
	
	public CartDao(int purseq, String custid, String proname) {
		super();
		this.purseq = purseq;
		this.custid = custid;
		this.proname = proname;
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
	
	
	

	//CART TABLE 클릭하였을경우 
	
	
		public CartDto cartTableClick() {
			CartDto CartDto = null; 
			String where = "SELECT purseq, custid, proname, purqty, purdate, category FROM purchase ";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement(); 
				
				ResultSet rs = stmt_mysql.executeQuery(where); 
				if(rs.next()) {
					int purseq = rs.getInt(1);
					String custid = rs.getString(2); 
					String proname = rs.getString(3);
					int purqty = rs.getInt(4);
					String purdate = rs.getString(5);
					String category = rs.getString(6);

		
					CartDto = new CartDto(purseq, custid, proname, purqty, purdate, category);
					
				}
				conn_mysql.close();
		
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			return CartDto; 						
		}	
		
		
	
	
	//CART TABLE 에서 아이템삭제 눌렀을 경우 PURCHASE table 에서 데이터에서 삭제 
	
	public boolean deleteAction() {
		
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			String A = "delete from purchase ";
			String B = " where purseq = ? AND custid =? and proname=? ";
			
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setInt(1, purseq);
			ps.setString(2, custid);
			ps.setString(3, proname);
			
			ps.executeUpdate(); 
			
			conn_mysql.close();
			
			
			
		}catch(Exception e) {
			return false; 
		}
		
		return true; 		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
		
		
		
		
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
