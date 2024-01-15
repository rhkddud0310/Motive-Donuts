package com.javalec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import com.javalec.common.ShareVar;
import com.javalec.dto.AccountDto;

public class AccountDao {

	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	// Field
	String custid;
	String custpw;
	String custname;
	String phone;
	String birthday;
	String question1;
	String answer1;
	String question2;
	String answer2;
	String joinactive;
	String modidate;
	Timestamp deactive;
	FileInputStream image;
	
	// Constructor
	public AccountDao() {
		
	}
	
	public AccountDao(String custid) {
		super();
		this.custid = custid;
	}
	
	
	public AccountDao(String custid, String custname) {
		super();
		this.custid = custid;
		this.custname = custname;
	}

	public AccountDao(String custid, String custname, String phone, String birthday, String question1, String answer1,
			String question2, String answer2) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
	}

	public AccountDao(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, String joinactive, String modidate, Timestamp deactive,
			FileInputStream image) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.joinactive = joinactive;
		this.modidate = modidate;
		this.deactive = deactive;
		this.image = image;
	}

	// Account쪽 id와 이름과 사진 조회
	public AccountDto showProfile1() {
		AccountDto accountDto = null;
		
		String where = "select custid, custname, image from customer where custid = '" + custid +"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where);
			
			if(rs.next()) {
				String custid = rs.getString(1);
				String custname = rs.getString(2);
				
				// file
				ShareVar.filename = ShareVar.filename + 1; // 맨처음 0 인데 불러올때 1씩 이름이 생겨서 들어간다
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(3);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				accountDto = new AccountDto(custid, custname);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return accountDto;
	}
	
	// MyProfile 개인정보 조회
	public AccountDto showProfile2() {
		AccountDto accountDto = null;
		
		String where = "select custid, custname, phone, birthday, question1, answer1, question2, answer2, image from customer where custid = '" + custid +"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where);
			
			if(rs.next()) {
				String custid = rs.getString(1);
				String custname = rs.getString(2);
				String phone = rs.getString(3);
				String birthday = rs.getString(4);
				String question1 = rs.getString(5);
				String answer1 = rs.getString(6);
				String question2 = rs.getString(7);
				String answer2 = rs.getString(8);
				
				// file
				ShareVar.filename = ShareVar.filename + 1; // 맨처음 0 인데 불러올때 1씩 이름이 생겨서 들어간다
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(9);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				accountDto = new AccountDto(custid, custname, phone, birthday, question1, answer1, question2, answer2);
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return accountDto;
	}
	
	// 탈퇴 처리
		public boolean deactiveAction() {
			PreparedStatement ps = null; // 보안상 사용, 불러올때는 그냥 Statement를 사용하면 됨

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

				String A = "UPDATE customer SET deactive = sysdate() WHERE custid = '" + custid +"'";;

				ps = conn_mysql.prepareStatement(A);
				ps.executeUpdate();

				conn_mysql.close();

			} catch (Exception e) {
					e.printStackTrace();
				return false;
			}
			return true;
		}
}
