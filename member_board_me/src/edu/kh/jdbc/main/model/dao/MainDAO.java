package edu.kh.jdbc.main.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;

public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MainDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Member login(Connection conn, String memberId, String memberPw) throws SQLException {

		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) 
				
				loginMember = new Member(rs.getInt("MEMBER_NO"),
							memberId,
							rs.getString("MEMBER_NM"),
							rs.getString("MEMBER_GENDER"),
							rs.getString("ENROLL_DATE"));
			 	
			}finally {
			 close(rs);
			 close(pstmt);
			}
		
		return loginMember;
	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
