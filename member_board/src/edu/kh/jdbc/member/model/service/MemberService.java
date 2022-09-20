package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;


public class MemberService {
	//2.
	private MemberDAO dao = new MemberDAO();

	
	// 0920 2교시 4... throws Exception 추가
 	/** 회원 목록 조회 서비스
 	 * @return memberList
 	 * @throws Exception
 	 */
 	public List<Member> selectAll() throws Exception {
 		Connection conn = getConnetcion(); // 커넥션 생성
 		
 		// DAO메서드 호출 후 결과 반환 받기
 		List<Member> memberList = dao.selectAll(conn);
 		
 		close(conn); // 커넥션 반환  insert update이런거엿으면 commit rollback해야함
 		
 		return memberList;
	}

	//0920 3교시 3.... 

	/**회원 정보 수정 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {
		// 커넥션 생성
		Connection conn = getConnetcion();
		
		// DAO 메서드 호출 후 결과 반환 받고
		int result = dao.updateMember(conn, member);		
		// 트랜잭션 제어 처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		
		// 커넥션 반환
		close(conn);
		// 수정 결과 반환
		return result;
		
		
		
	
	}
//    0920 4교시 2..
	/** 비밀번호 변경 서비스
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw1, int memberNo) throws Exception{
				// 커넥션 생성  // 위에꺼 복사@@
				Connection conn = getConnetcion();
				
				// DAO 메서드 호출 후 결과 반환 받고
				int result = dao.updatePw(conn, currentPw,newPw1, memberNo);
				
				// 트랜잭션 제어 처리
				if(result > 0) commit(conn);
				else rollback(conn);
				
				
				// 커넥션 반환
				close(conn);
				// 수정 결과 반환
				return result;
	}

	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int secession(String memberPw, int memberNo) throws Exception {
		
		Connection conn = getConnetcion();
		
		int result = dao.secession(conn, memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		
		return result;
	}
	
}
