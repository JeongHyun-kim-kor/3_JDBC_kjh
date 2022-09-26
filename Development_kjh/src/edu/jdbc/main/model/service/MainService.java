package edu.jdbc.main.model.service;

import java.sql.Connection;
			  
import static edu.jdbc.common.JDBCTemplate.*;

import edu.jdbc.main.model.dao.MainDAO;
import edu.jdbc.manager.vo.Manager;
import edu.jdbc.member.vo.Member;

public class MainService {

	private MainDAO dao = new  MainDAO(); 
	
	
	
	
	
	
	public Member login(String memberId, String memberPw) throws Exception {
		
		Connection conn = getConnetcion();
		
		Member loginMember = dao.login(conn,memberId, memberPw);
		
		close(conn);
		
		return loginMember;
	}






	public Manager managerLogin(String managerId, String managerPw)throws Exception{

		Connection conn = getConnetcion();
		
		Manager loginManager = dao.managerLogin(conn, managerId, managerPw);
		
		close(conn);
		
		return loginManager;
	}






	public int idDupCheck(String memberId) throws Exception {

		Connection conn = getConnetcion();
		
		int result = dao.idDupCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}






	public int signUp(Member member) throws Exception {

		Connection conn = getConnetcion();
		
		int result = dao.signUp(conn, member);
		
		if(result >0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	 
	
	
}
