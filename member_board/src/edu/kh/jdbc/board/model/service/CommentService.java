package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.getConnetcion;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Comment;

public class CommentService {

	// CommentDAO 객체 생성
	private CommentDAO dao = new CommentDAO();


	// 0923 1교시 3. //0923 2교시 1.
	public int insertComment(Comment comment) throws Exception{

		Connection conn = getConnetcion();
		//0923 2교시 1.
		int result = dao.insertComment(conn, comment);
		
		if (result > 0) commit(conn);
		else 			rollback(conn);
		
		
		return result;
	}

	//0923 3교시 2. 
	/** 댓글 수정 서비스
	 * @param commentNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(int commentNo, String content) throws Exception {

		int result = 0;
		
		Connection conn = getConnetcion();
		
		//0923 3교시 3. 
		result = dao.updateComment(conn, commentNo, content);
		
		if (result > 0) commit(conn);
		else 			rollback(conn);
		
		return result;
	}

	/** 댓글 삭제 서비스 -> update(컬럼 하나) 
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */// 0923 4교시 2
	public int delectComment(int commentNo) throws Exception {
		
		Connection conn = getConnetcion();
		
		int result = dao.delectComment(conn, commentNo);
		
		if(result >0) commit(conn);
		else 		  rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
