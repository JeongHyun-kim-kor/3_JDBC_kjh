package edu.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;

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

	

}
