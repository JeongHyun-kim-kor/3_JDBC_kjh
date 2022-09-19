package edu.kh.jdbc.main.model.service;

import java.sql.Connection;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.vo.Member;

public class MainService {

	private MainDAO dao = new MainDAO();
	
	
	
	public Member login(String memberId, String memberPw) throws Exception{
		
//		Connection conn = getConnection();
		Connection conn = getConnetcion();

		Member loginMember = dao.login(conn, memberId, memberPw);
		
		close(conn);
		
		return loginMember;
		
	}

}
