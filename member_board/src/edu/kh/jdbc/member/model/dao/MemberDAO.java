package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;

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
		
	}
	//// 0920 2교시 5... 
	/**
	 * @param conn
	 * @return memberList
	 * @throws Exception << 추가
	 */
	public List<Member> selectAll(Connection conn) throws Exception{

		// 결과 저장용 변수 선언
		List<Member> resultList = new ArrayList<>();

		try {
			// SQL얻어오기
			String sql = prop.getProperty("selectAll");
			// Statement 객체 생성
			stmt = conn.createStatement();
			// SQL(SELECT) 수행 후 결과ResultSet 반환 받기
			rs = stmt.executeQuery(sql);
			// 반복문(While)을 이용해서 조회 결과의 각 행에 접근 후
			// 컬럼 값을 얻어와 Member객체에 저장 후 List에 추가
			while(rs.next()) {
				
				String memberId = rs.getString("MEMBER_ID");
				String memberNm = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				Member mem = new Member(memberId, memberNm, memberGender);
				resultList.add(mem);
				// 생성자를 따로 만들었지만 다른 방법이 있다? 
			}
			
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과를 옮겨 담은 List 반환
		return resultList; //> memberList로 들어가나?
	}
	
	
}
