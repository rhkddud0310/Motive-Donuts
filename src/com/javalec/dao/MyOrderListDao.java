package com.javalec.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import com.javalec.common.ShareVar;
import com.javalec.dto.AccountDto;
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
			
			String where = "select SUM(accupoints) as totalAccu,  SUM(spendpoints) as totalSpend from myorder where custid = '" + custid + "'";
			
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
	
}
