package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public BoardDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception {
		//결과 저장용 변수 선언
		List<Board> boardList = new ArrayList<>();
		
		try {
			//SQL ~
			String sql = prop.getProperty("selectAllBoard");
			// Statement ~
			stmt = conn.createStatement();
			//SQL 수행 후 ResultSet ~
			rs = stmt.executeQuery(sql);
			
			// ResultSet에 저장된 값을 List옮겨담기~
			while(rs.next()) {
				
			int boardNo = rs.getInt("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String memberName = rs.getString("MEMBER_NM");
			int readCount = rs.getInt("READ_COUNT");
			String createDate = rs.getString("CREATE_DT");
			int commentCount = rs.getInt("COMMENT_COUNT");
			
			// 매개변수생성자? > XXX
			Board board = new Board();
			board.setBoardNo(boardNo);
			board.setBoardTitle(boardTitle);
			board.setMemberName(memberName);
			board.setReadCount(readCount);
			board.setCreateDate(createDate);
			board.setCommentCount(commentCount);
			
			
			
			boardList.add(board);
				
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		
			
		}
	
		
		
		
		return boardList;
	}

	/**
	 * @param conn
	 * @param boardNo
	 * @return Board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception{
		// 결과 저장용 변수 선언
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo); // ? 에 알맞은 값 대입
			
			rs = pstmt.executeQuery(); // SQL(Select) 수행 후 결과 반환받기
			
			if(rs.next()) {
				board = new Board();
				
			board.setBoardNo		(rs.getInt("BOARD_NO"));
			board.setBoardTitle		(rs.getString("BOARD_TITLE"));
			board.setBoardContent	(rs.getString("BOARD_CONTENT"));
			board.setMemberNo		(rs.getInt("MEMBER_NO"));
			board.setMemberName		(rs.getString("MEMBER_NM"));
			board.setReadCount		(rs.getInt("READ_COUNT"));
			board.setCreateDate		(rs.getString("CREATE_DT"));
				
//				BOARD_NO, BOARD_TITLE, BOARD_CONTENT,
//				MEMBER_NO, MEMBER_NM, READ_COUNT,
//				TO_CHAR(CREATE_DT, 'YYYY-MM-DD HH24:MI:SS') CREATE_DT
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		
		return board;
	}

	/** 조회 수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increseReadCount(Connection conn, int boardNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("increaseReadCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	/** 
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			// content title, boardNo
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		

		return result;
	}
}
