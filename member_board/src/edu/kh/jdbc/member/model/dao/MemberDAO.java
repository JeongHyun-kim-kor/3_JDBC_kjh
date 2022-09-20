package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MemberDAO {
	
	// 필드(== 멤버)
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() { // 기본생성자
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}// 1.
	
	
}
