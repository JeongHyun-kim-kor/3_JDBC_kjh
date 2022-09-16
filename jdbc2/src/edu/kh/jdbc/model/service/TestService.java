package edu.kh.jdbc.model.service;

// import static 구문
// -> static이 붙은 필드, 클래스, 메서들을 호출할 때
//    클래스명을 생략할 수 있게 하는 구문

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.vo.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

// Service : 비즈니스 로직(데이터 가공, 트랜잭션 제어) 처리
// -> 실제 프로그램이 제공하는 기능을 모아놓은 클래스

// 하나의 Service 메서드에서 n개의 DAO메서드(지정된 SQL을 수행)를 호출하여
// 이를 하나의 트랜잭션 단위로 취급하여
// 한번에 commit, rollback을 수행할 수 있다.

// * 여러 DML을 수행하지 않는 경우(단일 DML, SELECT 등)라도
// 코드의 통일성을 지키기 위해서 Service에 Connection객체를 생성한다.

// -> Connection 객체가 commit, rollback객체를 생성한다.


public class TestService {

	private TestDAO dao= new TestDAO();
	
	/** 1행 삽입 서비스
	 * @param vo1
	 * @return result
	 */
	public int insert(TestVO vo1) {
		
		// 1. Connection 생성(항상!!!!!!!!!!!!)
		Connection conn = getConnetcion();
						// 클래스명. 메서드명  [static]
		
		// INSERT DAO 메서드를 호출하여 수행 후 결과 반환 받기
		// -> Service에서 생성한 Connection 객체를 반드시 같이 전달해야 한다!
		int result = dao.insert(conn, vo1); // conn을 같이보내줘야 dao에서 활용 가능
		// DAO 
		// result = DAO의 SQL 수행 후 반영된 결과 행의 개수
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		
		// return 하기 전에 항상!!
		// 커넥션 반환(close)
		close(conn);
		
		// 결과 반환
		return result;
	}

	
}
