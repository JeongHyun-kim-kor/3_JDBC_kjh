package edu.jdbc.main.model.dao;
import static edu.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.jdbc.manager.vo.Manager;
import edu.jdbc.member.vo.Member;

public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member login(Connection conn, String memberId, String memberPw) throws Exception{
		
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				Member member = new Member();
				
				loginMember = new Member(rs.getInt("MEMBER_NO"), 
										memberId,
										rs.getString("MEMBER_NAME"),
										rs.getString("MEMBER_GENDER"),
										rs.getString("PHONE"),
										rs.getString("EMAIL"),	
									rs.getString("ENROLL_DATE"));
							
				
			} 
		}finally{
			close(rs);
			close(pstmt);
			}
		
		
		
		return loginMember;
	}

	public Manager managerLogin(Connection conn, String managerId, String managerPw) throws Exception {

		Manager loginManager = null;
		
		try {
			String sql = prop.getProperty("managerLogin");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, managerId);
			pstmt.setString(2, managerPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginManager = new Manager(rs.getInt("MANAGER_NO"),
										managerId,
										rs.getString("MANAGER_NAME"),
										rs.getString("MANAGER_GENDER"),
										rs.getString("ENROLL_DATE"));	
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return loginManager;
	}

	public int idDupCheck(Connection conn, String memberId) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int signUp(Connection conn, Member member) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String findId(Connection conn, String name, String emailPhone) throws Exception {
		
		try {
			
			String sql = prop.getProperty("findId");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			
		}
		finally {
			close(rs);
			close(stmt);
		}
		
		
		
		return null;
	}

	public String findIdByEmail(Connection conn, String name, String emailPhone) throws Exception {
		
		String id = "";
		try {
			String sql =prop.getProperty("findIdByEmail");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emailPhone);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				Member member =new Member();
				
				id = rs.getString("MEMBER_ID");
				
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}

	public String findIdByPhone(Connection conn, String name, String phone) throws Exception {
		String id = "";
		try {
			String sql =prop.getProperty("findIdByPhone");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				Member member =new Member();
				
				id = rs.getString("MEMBER_ID");
				
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}

	public String findPwByEmail(Connection conn,String id, String name, String email) throws Exception {
		
		String Pw = null;
		
		try {
			String sql = prop.getProperty("findPwByEmail");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Pw = rs.getString("MEMBER_PW");
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return Pw;
	}
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
