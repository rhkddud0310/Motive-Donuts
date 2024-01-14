package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.javalec.common.ShareVar;
import com.javalec.dto.MenuListViewDto;

public class MenuDao {
	
	static { // when class loaded
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) { // checked exception (required)
			e.printStackTrace();
		}
	}
	
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	// Constructor
	public MenuDao() {
		
	}
	
	public static void main(String[] args) {
		MenuDao dao = new MenuDao();

		List<MenuListViewDto> listByCategory = dao.selectAllByCategory("도넛");
		List<MenuListViewDto> listByKeyword = dao.searchAllByCategoryOrName("스트로");
		
		System.out.printf("""
				List by Category "도넛": %s
				List by Keyword "스트로": %s
				""", listByCategory, listByKeyword);
	}
	
	// *******************************************************************************************************************
	// Method
	/**
	 * 
	 * @param categoryId
	 * @return 선택한 카테고리 상품 목록
	 */
	public List<MenuListViewDto> selectAllByCategory(String categoryId) {
		Objects.requireNonNull(categoryId);
		// JDK 15+
		String sql = """
				SELECT
					p.proname,
					p.engproname,
					p.sellprice,
					p.imagename,
					p.image as imageFile,
					c.item as categoryName
				FROM product p
					INNER JOIN 	sell s
					ON 			p.proname = s.proname
					INNER JOIN 	category c
					ON 			s.cateitem = c.item
				WHERE c.item = ?
				""";
		
		try (
				// DB에 접속할 때마다 커넥션을 매번 만들어서 -> 매번 close 했단 사실.
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				PreparedStatement ps = conn_mysql.prepareStatement(sql);
		) {
			ps.setString(1, categoryId);
			
			List<MenuListViewDto> list = getMenuListFromPreparedStatement(ps);
			
			return list;
		} catch (Exception e) {
			// 리스트 종류는: null보다는 빈 리스트 반환
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public List<MenuListViewDto> searchAllByCategoryOrName(String keyword) {
		if (keyword == null || keyword.length() == 0) {
			// 검색창에 아무것도 안 썼을 땐 빈 화면
			return Collections.emptyList();
		}
		
		// JDK 15+
		String sql = """
				SELECT
					p.proname,
					p.engproname,
					p.sellprice,
					p.imagename,
					p.image as imageFile,
					c.item as categoryName
				FROM product p
					INNER JOIN 	sell s
					ON 			p.proname = s.proname
					INNER JOIN 	category c
					ON 			s.cateitem = c.item
				WHERE 	c.item LIKE CONCAT('%', ?, '%')
					OR 	p.proname LIKE CONCAT('%', ?, '%')
					OR	p.engproname LIKE CONCAT('%', ?, '%')
				ORDER BY c.item, p.proname
				""";
		
		try (
				// DB에 접속할 때마다 커넥션을 매번 만들어서 -> 매번 close 했단 사실.
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				PreparedStatement ps = conn_mysql.prepareStatement(sql);
		) {
			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setString(3, keyword);
			
			List<MenuListViewDto> list = getMenuListFromPreparedStatement(ps);
			
			return list;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	private List<MenuListViewDto> getMenuListFromPreparedStatement(PreparedStatement ps) throws SQLException {
		try (ResultSet rs = ps.executeQuery()) {
			ArrayList<MenuListViewDto> list = new ArrayList<>();
			
			while (rs.next()) {
				String proname = rs.getString("proname");
				String engproname = rs.getString("engproname");
				Integer sellprice = rs.getInt("sellprice");
				if (rs.wasNull()) {
					sellprice = null;
				}
				String imagename = rs.getString("imagename");
				byte[] imageFile = rs.getBytes("imageFile");
				String categoryName = rs.getString("categoryName");
				
				MenuListViewDto item = new MenuListViewDto(
						proname,
						engproname,
						sellprice,
						imagename,
						imageFile,
						categoryName
				);
				
				list.add(item);
			}
			
			return list;
		}
	}
}
