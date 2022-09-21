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
				
				Member member = new Member(); // >> 매개변수생성자 만들필요없다. 밑에처럼
				member.setMemberId(memberId);
				member.setMemberName(memberNm);
				member.setMemberGender(memberGender);
//				Member mem = new Member(memberId, memberNm, memberGender);
				resultList.add(member);
				// 생성자를 따로 만들었지만 다른 방법이 있다? 
			}
			
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		//0920 3교시 4.... 

		// 조회 결과를 옮겨 담은 List 반환
		return resultList; //> memberList로 들어가나?
	}
	/**
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception {
		
		// 결과 수정용 변수 생성
		int result = 0; // UPDATE 바녕 결과 행의 개수(정수형)을 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updateMember");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2,  member.getMemberGender());
			pstmt.setInt(3,  member.getMemberNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
		} finally {
			
			//JDBC객체 자원 반환
			close(pstmt);
		}
		
		
		// 수정 결과 반환
		return result;
	}
	//    0920 4교시 3..

	/** 비밀변호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, String currentPw, String newPw1, int memberNo) throws Exception {
		
		// 결과 수정용 변수 생성
				int result = 0; // UPDATE 바녕 결과 행의 개수(정수형)을 저장하기 위한 변수
				
				try {
					// SQL 얻어오기
					String sql = prop.getProperty("updatePw"); // 복사 후 변경
					
					// PreparedStatement 객체 생성
					pstmt = conn.prepareStatement(sql);
					
					// ? 알맞은 값 대입
					pstmt.setString(1, newPw1);
					pstmt.setInt(2, memberNo);
					pstmt.setString(3,  currentPw);
					
					// SQL 수행 후 결과 반환 받기
					result = pstmt.executeUpdate();
				} finally {
					
					//JDBC객체 자원 반환
					close(pstmt);
				}
				
				
				// 수정 결과 반환
				return result;
	}
	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return 
	 * @throws Exception
	 */
	public int secession(Connection conn, String memberPw, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
