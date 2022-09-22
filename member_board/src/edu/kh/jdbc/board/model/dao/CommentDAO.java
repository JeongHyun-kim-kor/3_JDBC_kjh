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
}

