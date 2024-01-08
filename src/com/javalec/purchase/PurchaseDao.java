package com.javalec.purchase;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;

public class PurchaseDao {

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
	
	
	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}


	
	//Constructor
	
	
	public PurchaseDao(String image, String proname, int sellprice, int purqty) {
		super();
		this.image = image;
		this.proname = proname;
		this.sellprice = sellprice;
		this.purqty = purqty;
	}
	
	
	
	
	public PurchaseDao(int point) {
		super();
		this.point = point;
	}
	
	
	
	//Method
	


	//카트 내역을 구매하기 창으로 불러오자. 
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
	
	
	
	//마이 포인트 표시 
	
	
	public PurchaseDto myPoints() {
		PurchaseDto purchaseDto = null; 
		String where = "select sum( " + point;
		String where2 = " ) from myorder where custid = 'jojo' ";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			ResultSet rs = stmt_mysql.executeQuery(where+where2); 
			if(rs.next()) {
				int point = rs.getInt(1);
			
				purchaseDto = new PurchaseDto(point);
			}
			conn_mysql.close();
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return purchaseDto; 						
	}
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}