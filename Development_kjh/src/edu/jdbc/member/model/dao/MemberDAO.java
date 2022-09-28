package edu.jdbc.member.model.dao;

import static edu.jdbc.common.JDBCTemplate.close;
import static edu.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import edu.jdbc.manager.model.service.ManagerService;
import edu.jdbc.manager.view.ManagerView;
import edu.jdbc.product.vo.Product;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private Properties prop;
	
	private ManagerService mService = null;
	private ManagerView mView = null;
	
	public MemberDAO() {
		try { 
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	

	
	public int buyProduct(Connection conn, Product pd , int memberNo) throws Exception {

			int result = 0;
		
		try {
			String sql = prop.getProperty("buyProduct");
		
			//카테고리/ 제품명, 개수 /가격s
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setInt(1, memberNo); // 다른 테이블에 있는 값
			pstmt.setString(2, pd.getProductName()); // 입력값
			pstmt.setInt(3, pd.getProductStock()); // 입력값
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updatePw(Connection conn, String currentPw, String newPw1, int memberNo) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw1); // 변경될 비밀번호 ? 자리
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw); // 현재 비밀번호 자리
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int unregister(Connection conn, String memberPw, int memberNo) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("unregister");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		} finally{
			close(pstmt);
			
		}
		
		
		
		return result;
	}

}
