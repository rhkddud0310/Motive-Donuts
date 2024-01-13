package com.javalec.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.javalec.common.ShareVar;

public class SignDao {

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
	public SignDao() {

	}

	public SignDao(String custid) {
		super();
		this.custid = custid;
	}
	
//	public SignDao(String custpw) {
//		super();
//		this.custpw = custpw;
//	}

	public SignDao(String custid, String custname, String phone, String birthday, String question1, String answer1,
			String question2, String answer2, Timestamp deactive) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.deactive = deactive;
	}

	public SignDao(String custid, String custname, String question1, String answer1, String question2, String answer2,
			Timestamp deactive) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.deactive = deactive;
	}

	
	
	public SignDao(String custid,  String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, Timestamp deactive, String custpw) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.deactive = deactive;
		this.custpw = custpw;
	}

	public SignDao(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, FileInputStream image) {
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
		this.image = image;
	}

	public SignDao(String custid, String custpw, String custname, String phone, String birthday, String question1,
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

	// Method
	// 회원 등록
	public boolean insertAction() {
		PreparedStatement ps = null; // 보안상 사용, 불러올때는 그냥 Statement를 사용하면 됨

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			String A = "insert into customer (custid, custpw, custname, phone, birthday, question1, answer1, question2, answer2, joinactive, image";
			String B = ") values (?,?,?,?,?,?,?,?,?,sysdate(),?)";

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, custid);
			ps.setString(2, custpw);
			ps.setString(3, custname);
			ps.setString(4, phone);
			ps.setString(5, birthday);
			ps.setString(6, question1);
			ps.setString(7, answer1);
			ps.setString(8, question2);
			ps.setString(9, answer2);
			ps.setBinaryStream(10, image);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
//				e.printStackTrace();
			return false;
		}
		return true;
	}

	// 아이디 중복 체크
	public boolean sameCheckAction(String custid) {
		PreparedStatement ps = null; // 보안상 사용, 불러올때는 그냥 Statement를 사용하면 됨
		boolean available = false;
		ResultSet rs = null;
		Connection conn = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String sql = "SELECT custid, deactive FROM customer WHERE custid = ? AND (deactive IS NOT NULL OR deactive IS NULL)";

			// PreparedStatement 준비
			ps = conn.prepareStatement(sql);
			ps.setString(1, custid);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			// 결과 확인
			if (rs.next()) {
				// 아이디가 존재하는 경우
				Timestamp deactive = rs.getTimestamp("deactive");
				if (deactive != null) {
					// 회원이 탈퇴한 경우
					JOptionPane.showMessageDialog(null, custid + "는 탈퇴한 회원입니다.");
					available = false;
				} else {
					// 회원이 활성 상태인 경우
					JOptionPane.showMessageDialog(null, custid + "는 중복된 아이디입니다.");
					available = false;
				}
			} else if (custid.length() == 0) {
				// 공백만 입력 받았을때 처리
				JOptionPane.showMessageDialog(null, "잘못된 아이디입니다.");
				available = false;

			} else {
				available = true;
				// 아이디가 존재하지 않는 경우 true
				JOptionPane.showMessageDialog(null, custid + "는 사용할 수 있는 아이디입니다.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return available;
	}

	public boolean loginCheckAction(String custid1, String custpw1) {
		PreparedStatement ps = null;
		boolean available = false;
		Connection conn = null;
		ResultSet rs = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String query = "select custid, custpw, deactive from customer "; // 뒤에 띄어쓰기 한칸 할것
			String query1 = " where custid = ? and custpw = ?";

			// PreparedStatement 준비
			ps = conn.prepareStatement(query + query1);
			ps.setString(1, custid1);
			ps.setString(2, custpw1);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			if (rs.next()) {
				Timestamp deactive = rs.getTimestamp("deactive");
				// 회원이 탈퇴한 경우
				if (deactive != null) {
					JOptionPane.showMessageDialog(null, custid1 + "는 탈퇴한 회원입니다.");
					available = false;
				} else {
					// 아이디와 비밀번호가 존재하는 경우
					available = true;
					JOptionPane.showMessageDialog(null, "로그인을 환영합니다.");

				}
			} else {
				// 아이디와 비밀번호가 존재하지 않는 경우
				available = false;
//				JOptionPane.showMessageDialog(null, "정보가 잘못 입력되었습니다. 다시 입력하여 주세요.");

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 확인
		return available;
	}

	// 이름으로 아이디 찾기
	public SignDao findIdFromName(String custname, String question, String answer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SignDao user = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String query = "SELECT custid, custname, question1, answer1, question2, answer2, deactive "
					+ "FROM customer " + "WHERE custname = ? AND ((" + "question1 = ? AND answer1 = ?) OR "
					+ "(question2 = ? AND answer2 = ?))";

			// PreparedStatement 준비
			ps = conn.prepareStatement(query);
			ps.setString(1, custname);
			ps.setString(2, question);
			ps.setString(3, answer);
			ps.setString(4, question);
			ps.setString(5, answer);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			// 결과 확인
			if (rs.next()) {
				// 회원이 존재하는 경우 해당 정보로 SignDao 객체 생성
				user = new SignDao(rs.getString("custid"), rs.getString("custname"), rs.getString("question1"),
						rs.getString("answer1"), rs.getString("question2"), rs.getString("answer2"),
						rs.getTimestamp("deactive"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return user;
	}

	// 전화번호로 아이디 찾기
	public SignDao findIdFromPhone(String phone, String question, String answer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SignDao user = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String query = "SELECT custid, phone, question1, answer1, question2, answer2, deactive " + "FROM customer "
					+ "WHERE phone = ? AND ((" + "question1 = ? AND answer1 = ?) OR "
					+ "(question2 = ? AND answer2 = ?))";

			// PreparedStatement 준비
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			ps.setString(2, question);
			ps.setString(3, answer);
			ps.setString(4, question);
			ps.setString(5, answer);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			// 결과 확인
			if (rs.next()) {
				// 회원이 존재하는 경우 해당 정보로 SignDao 객체 생성
				user = new SignDao(rs.getString("custid"), rs.getString("phone"), rs.getString("question1"),
						rs.getString("answer1"), rs.getString("question2"), rs.getString("answer2"),
						rs.getTimestamp("deactive"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return user;
	}

	// 생일로 아이디 찾기
	public SignDao findIdFromBirthday(String birthday, String question, String answer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SignDao user = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String query = "SELECT custid, birthday, question1, answer1, question2, answer2, deactive "
					+ "FROM customer " + "WHERE birthday = ? AND ((" + "question1 = ? AND answer1 = ?) OR "
					+ "(question2 = ? AND answer2 = ?))";

			// PreparedStatement 준비
			ps = conn.prepareStatement(query);
			ps.setString(1, birthday);
			ps.setString(2, question);
			ps.setString(3, answer);
			ps.setString(4, question);
			ps.setString(5, answer);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			// 결과 확인
			if (rs.next()) {
				// 회원이 존재하는 경우 해당 정보로 SignDao 객체 생성
				user = new SignDao(rs.getString("custid"), rs.getString("birthday"), rs.getString("question1"),
						rs.getString("answer1"), rs.getString("question2"), rs.getString("answer2"),
						rs.getTimestamp("deactive"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return user;
	}

	// 회원 상태 확인 메소드
	public String getMemberStatus() {
		if (deactive == null) {
			// 회원인 경우
			return "회원";
		} else {
			// 탈퇴한 경우
			return "탈퇴";
		}
	}

	// 아이디 반환
	public String getCustid() {
		return custid;
	}
	
	// 비밀번호 반환
	public String getCustpw() {
		return custpw;
	}

	
	// 비밀번호 찾기
	public SignDao findPw(String custid, String custname, String phone, String birthday, String question, String answer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SignDao user = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 데이터베이스 연결
			conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

			// SQL 문장
			String query = "SELECT custid, custname, phone, birthday, question1, answer1, question2, answer2, deactive, custpw "
					+ "FROM customer WHERE custid = ? And custname = ? AND phone = ? And birthday = ? And "
							+ "((question1 = ? AND answer1 = ?) OR (question2 = ? AND answer2 = ?))";

			// PreparedStatement 준비
			ps = conn.prepareStatement(query);
			ps.setString(1, custid);
			ps.setString(2, custname);
			ps.setString(3, phone);
			ps.setString(4, birthday);
			ps.setString(5, question);
			ps.setString(6, answer);
			ps.setString(7, question);
			ps.setString(8, answer);

			// 쿼리 실행 및 결과 가져오기
			rs = ps.executeQuery();

			// 결과 확인
			if (rs.next()) {
				// 회원이 존재하는 경우 해당 정보로 SignDao 객체 생성
				user = new SignDao(rs.getString("custid"), rs.getString("custname"), rs.getString("phone"),
						rs.getString("birthday"), rs.getString("question1"), rs.getString("answer1"), 
						rs.getString("question2"), rs.getString("answer2"), rs.getTimestamp("deactive"), rs.getString("custpw"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return user;
	}
	
} // End
