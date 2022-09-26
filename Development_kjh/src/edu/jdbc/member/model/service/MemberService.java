package edu.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import static edu.jdbc.common.JDBCTemplate.*;

import edu.jdbc.member.model.dao.MemberDAO;
import edu.jdbc.product.vo.Product; 

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	
	public int buyProduct(Product pd) throws Exception{

		Connection conn = getConnetcion();
		
		int result = dao.buyProduct(conn,pd);
		
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int updatePw(String currentPw, String newPw1, int memberNo) throws Exception{
		
		Connection conn = getConnetcion();
		
		int result = dao.updatePw(conn,currentPw,newPw1, memberNo);
		
		if(result>0) commit(conn);
		else       rollback(conn);
		
		return result;
	}

	public int unregister(String memberPw, int memberNo) throws Exception {

		Connection conn = getConnetcion();
		
		int result = dao.unregister(conn,memberPw,memberNo);
		
		if( result >0) commit(conn);
		else          rollback(conn);
		
		return result;
	}



	

	

}
