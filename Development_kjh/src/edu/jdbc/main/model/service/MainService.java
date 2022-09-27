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




	public String findIdByEmail(String name, String email) throws Exception {
		Connection conn = getConnetcion();
		String Id = null;
		if(email.contains("@")) {
			Id = dao.findIdByEmail(conn, name,email);
		} else {
			System.out.println("[이메일 형식에 맞게 입력해주세요]");
		}
		
		
		return Id;
	}


	public String findIdByPhone(String name, String phone) throws Exception {
		Connection conn = getConnetcion();
		String Id = null;
		
		if(phone.contains("010")) {
			Id = dao.findIdByPhone(conn, name,phone);

		} else {
			System.out.println("[010을 포함한 휴대폰 번호를 입력해주세요.]");
		}
		close(conn);
		
		return Id;

				
				
	}






	public String findPwByEmail(String id, String name, String email) throws Exception{

		Connection conn = getConnetcion();
		String Pw = null;
			
		if(email.contains("@")) {
			Pw = dao.findPwByEmail(conn,id, name,email);

		} else {
			System.out.println("[이메일 형식에 맞게 입력해주세요]");
		}
		close(conn);
		
		return Pw;
	}
	
	 
	
	
}
