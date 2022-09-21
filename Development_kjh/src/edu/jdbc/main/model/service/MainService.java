package edu.jdbc.main.model.service;

import java.sql.Connection;
			  
import static edu.jdbc.common.JDBCTemplate.*;

import edu.jdbc.main.model.dao.MainDAO;
import edu.jdbc.member.vo.Member;

public class MainService {

	private MainDAO dao = new  MainDAO(); 
	
	public Member login(String memberId, String memberPw) throws Exception {
		
		Connection conn = getConnetcion();
		
		Member loginMember = dao.login(conn,memberId, memberPw);
		
		close(conn);
		
		return loginMember;
	}
	
	
}
