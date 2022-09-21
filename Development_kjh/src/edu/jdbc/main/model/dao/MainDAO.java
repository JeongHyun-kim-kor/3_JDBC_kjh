package edu.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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
			// TODO: handle exception
		}
	}
	
	public Member login(Connection conn, String memberId, String memberPw) {
		
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				login
				
			}
			
		}catch(Exception	e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
