package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.dto.MyOrderListDto;

public class MyOrderListDao {
	
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	// Field
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
	public MyOrderListDao() {
		// TODO Auto-generated constructor stub
	}
	
	public MyOrderListDao(String proname, String payment, int payprice, int spendpoints, int accupoints,
			Timestamp orderdate) {
		super();
		this.proname = proname;
		this.payment = payment;
		this.payprice = payprice;
		this.spendpoints = spendpoints;
		this.accupoints = accupoints;
		this.orderdate = orderdate;
	}


	public MyOrderListDao(String custid,  int accupoints, int spendpoints) {
		super();
		this.custid = custid;
		this.accupoints = accupoints;
		this.spendpoints = spendpoints;
	}



	public MyOrderListDao(int orderseq, String custid, String proname, String payment, int payprice, int spendpoints,
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
	
	// Method
	// Account쪽 나의 포인트 조회
		public MyOrderListDto myPoints() {
			MyOrderListDto myOrderListDto = null;
			
			String where = "select SUM(accupoints) as totalAccu,  SUM(spendpoints) as totalSpend from myorder where custid = '" + ShareVar.loginID + "'";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				ResultSet rs = stmt_mysql.executeQuery(where);
				
				if(rs.next()) {
					int accupoints = rs.getInt("totalAccu");
					int spendpoints = rs.getInt("totalSpend");
					
					
					myOrderListDto = new MyOrderListDto(custid, accupoints, spendpoints );
				}
				conn_mysql.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return myOrderListDto;
		}
	
		public ArrayList<MyOrderListDto> selectList() {
			ArrayList<MyOrderListDto> dtoList = new ArrayList<MyOrderListDto>();
			String whereDefault = "select proname, payment, payprice, spendpoints, accupoints, orderdate from myorder where custid = '" + ShareVar.loginID + "'";
			System.out.println(whereDefault);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				ResultSet rs = stmt_mysql.executeQuery(whereDefault);
				
				while(rs.next()) {
					String proname = rs.getString(1);
					String payment = rs.getString(2);
					int payprice = rs.getInt(3);
					int spendpoints = rs.getInt(4);
					int accupoints = rs.getInt(5);
					Timestamp orderdate  = rs.getTimestamp(6);
					
					MyOrderListDto myOrderListDto = new MyOrderListDto(proname, payment, payprice, spendpoints, accupoints, orderdate);
					dtoList.add(myOrderListDto);
				}
				conn_mysql.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return dtoList;
		}
}
