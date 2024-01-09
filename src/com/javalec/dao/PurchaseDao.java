package com.javalec.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.dto.PurchaseDto;

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
	int spendpoints;
	
	
	int orderseq; 
	String payment; 
	int payprice;	
	int accupoints; 
	String orderdate;
	

	
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
	

	
	
	public PurchaseDao(int spendpoints) {
		super();
		this.spendpoints = spendpoints;
	}
	
	
	

	
	
	
	public PurchaseDao(String custid, String proname, int spendpoints, int orderseq, String payment, int payprice,
			int accupoints, String orderdate) {
		super();
		this.custid = custid;
		this.proname = proname;
		this.spendpoints = spendpoints;
		this.orderseq = orderseq;
		this.payment = payment;
		this.payprice = payprice;
		this.accupoints = accupoints;
		this.orderdate = orderdate;
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
	
	
	public int myPoints() {
		int returnPoint=0; 
		String where = "select sum(accupoints) from myorder where custid = 'jojo' ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			ResultSet rs = stmt_mysql.executeQuery(where); 
			if(rs.next()) {
				returnPoint = rs.getInt(1);
			}
			conn_mysql.close();
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return returnPoint; 						
	}
	

	
	//총금액을 표시하자. 
	
	public int sumPrice() {
		int sumprice=0; 
		String where = " SELECT sum(pr.sellprice) FROM purchase p, product pr WHERE pr.proname = p.proname and custid = 'jojo' ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			ResultSet rs = stmt_mysql.executeQuery(where); 
			if(rs.next()) {
				sumprice = rs.getInt(1);
			}
			conn_mysql.close();
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return sumprice; 						
	}
	
	
	
	//결제하기 눌렀을 경우 orders table 데이터 값으로 넣어주자. 

	public boolean ordersUpdate() {
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			String A = "insert into myorder (orderseq, custid, proname, payment, payprice, spendpoints, accupoints, orderdate";
			String B = ") values (?,?,?,?,?,?,?,?)";
			
				
			ps = conn_mysql.prepareStatement(A+B);
			ps.setInt(1, orderseq);
			ps.setString(2, custid);
			ps.setString(3, proname);
			ps.setString(4, payment);
			ps.setInt(5, payprice);
			ps.setInt(6, spendpoints);
			ps.setInt(7, accupoints);
			ps.setString(8, orderdate);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
			return false;
		}
		
		return true;	
	}
	
	
	
	
	
	
	
	//결제하기 눌렀을 경우 accupoints 데이터 값 update 해주자. 
	
	
	
	
	
	
	
	
	
	
	
	
	
}
