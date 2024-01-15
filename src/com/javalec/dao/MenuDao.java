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
import java.util.Optional;

import com.javalec.common.ShareVar;
import com.javalec.dto.MenuDetailedViewDto;
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
		List<MenuListViewDto> listByKeyword = dao.searchAllByCategoryOrName("스트로", "전체");
		Optional<MenuDetailedViewDto> detailMenu = dao.selectById("딸기 딜라이트 요거트 블렌디드");
		
		System.out.printf("""
				List by Category "도넛": %s
				List by Keyword "스트로": %s
				Detail Menu "딸기 딜라이트 요거트 블렌디드": %s
				""", listByCategory, listByKeyword, detailMenu);
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
					DISTINCT	p.proname,
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
					AND DATE_FORMAT(s.selldate, '%%Y-%%m-%%d')
									= DATE_FORMAT(current_timestamp(), '%%Y-%%m-%%d')
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
	
	public List<MenuListViewDto> searchAllByCategoryOrName(String keyword, String categoryName) {
		if (keyword == null || keyword.length() == 0) {
			// 검색창에 아무것도 안 썼을 땐 빈 화면
			return Collections.emptyList();
		}
		
		// JDK 15+
		String categoryFilter = !"전체".equals(categoryName) ?
				String.format("AND c.item = '%s'", categoryName) 
				: "";
		
		String sql = String.format("""
				SELECT
					DISTINCT	p.proname,
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
				WHERE (
						c.item LIKE CONCAT('%%', ?, '%%')
						OR 	p.proname LIKE CONCAT('%%', ?, '%%')
						OR	p.engproname LIKE CONCAT('%%', ?, '%%')
					)
					AND	DATE_FORMAT(s.selldate, '%%Y-%%m-%%d')
									= DATE_FORMAT(current_timestamp(), '%%Y-%%m-%%d')
					%s
				ORDER BY c.item, p.proname
				""", categoryFilter);
		
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
	
	public Optional<MenuDetailedViewDto> selectById(String productId) {
		String sql = """
				SELECT
					DISTINCT	p.proname,
								p.engproname,
								p.sellprice,
								p.detail,
								p.nutritional,
								p.ingredient,
								p.image as imageFile,
								p.imagename,
								c.item as categoryName
				FROM product p
					INNER JOIN 	sell s
					ON 			p.proname = s.proname
					INNER JOIN 	category c
					ON 			s.cateitem = c.item
				WHERE p.proname = ?
					AND	DATE_FORMAT(s.selldate, '%%Y-%%m-%%d')
									= DATE_FORMAT(current_timestamp(), '%%Y-%%m-%%d')
				""";
		
		try (
				// DB에 접속할 때마다 커넥션을 매번 만들어서 -> 매번 close 했단 사실.
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				PreparedStatement ps = conn_mysql.prepareStatement(sql);
		) {
			ps.setString(1, productId);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					return Optional.empty();
				}
				
				String proname = rs.getString("proname");
				String engproname = rs.getString("engproname");
				Integer sellprice = rs.getInt("sellprice");
				if (rs.wasNull()) {
					sellprice = null;
				}
				String detail = rs.getString("detail");
				String nutritional = rs.getString("nutritional");
				String ingredient = rs.getString("ingredient");
				String imagename = rs.getString("imagename");
				byte[] imageFile = rs.getBytes("imageFile");
				String categoryName = rs.getString("categoryName");
				
				MenuDetailedViewDto result = new MenuDetailedViewDto(
						proname,
						engproname,
						sellprice,
						detail,
						nutritional,
						ingredient,
						imageFile,
						imagename,
						categoryName
				);
				
				return Optional.of(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
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
