package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Board;

public class BoardService {

	// boardDAO 객체 생성
	private BoardDAO dao = new BoardDAO();
	
	// CommentDAO 객체 생성 -> 상세 조회 시 댓글 목록 조회용도로 사용
	private CommentDAO cDao = new CommentDAO();

	/** 게시글 목록 조회 서비스
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard() throws Exception{
		
		Connection conn = getConnetcion();
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Board> boardList = dao.selectAllBoard(conn);
		
		close(conn);
		
		return boardList;
		
	} 
	
	
	
	
	
}
