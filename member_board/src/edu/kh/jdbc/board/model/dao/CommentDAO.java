package edu.kh.jdbc.board.model.dao;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Comment;

public class CommentDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public CommentDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("comment-query.xml"));
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/** 특정 게시글 댓글 목록 조회(작성일 오름차순)
	 * @param conn
	 * @param boardNo
	 * @return comment
	 * @throws Exception
	 */
	public List<Comment> selectCommentList(Connection conn, int boardNo) throws Exception {
		
		List<Comment> commentList = new ArrayList<>();
		
		try {
		String sql = prop.getProperty("selectCommentList");
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, boardNo);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			Comment comment = new Comment();
			
//			int commentNo = rs.getInt("COMMENT_NO");
//			String commentContent = rs.getString("COMMENT_CONTENT");
//			int memberNo = rs.getInt("MEMBER_NO");
//			String createDate = rs.getString("CREATE_DT");
//			
//			comment.setCommentNo(commentNo);
//			comment.setCommentContent(commentContent);
//			comment.setMemberNo(memberNo);
//			comment.setCreateDate(createDate);
			
			
			comment.setCommentNo(rs.getInt(1));
			comment.setCommentContent(rs.getString(2));
			comment.setMemberNo(rs.getInt(3));
			comment.setMemberName(rs.getString(4));
			comment.setCreateDate(rs.getString(5));
			
			commentList.add(comment);
		}
		}finally{
			close(rs);
			close(pstmt);
		}
			
		return commentList;
	}

	// 0923 1교시 4.
	//0923 2교시 1.
	/** 댓글 등록 DAO
	 * @param conn
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int insertComment(Connection conn, Comment comment) throws Exception{

		int result = 0;
		try {
			String sql = prop.getProperty("insertComment");
			
			pstmt = conn.prepareStatement(sql);
			
			// 순서 : comment내용, 멤버넘버, 보드넘버
			pstmt.setString(1, comment.getCommentContent() );
			pstmt.setInt(2, comment.getMemberNo());
			pstmt.setInt(3, comment.getBoardNo());
		
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateComment(Connection conn, int commentNo, String content) throws Exception {
		int result = 0;
		
		try {
		String sql = prop.getProperty("updateComment");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, content);
		pstmt.setInt(2, commentNo	);
		result = pstmt.executeUpdate();
		
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}

