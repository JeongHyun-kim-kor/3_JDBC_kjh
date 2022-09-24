package edu.jdbc.manager.model.service;

import static edu.jdbc.common.JDBCTemplate.getConnetcion;

import java.sql.Connection;


import java.util.List;
import static edu.jdbc.common.JDBCTemplate.*;
import edu.jdbc.manager.model.dao.ManagerDAO;
import edu.jdbc.product.vo.Product;

public class ManagerService {

	private ManagerDAO dao = new ManagerDAO();
	
	public List<Product> selectAll() throws Exception{

			
		Connection conn =  getConnetcion(); 
		
		List<Product> productList = dao.selectAll(conn);
		
		close(conn);
		
		return productList;
	}

	public int manageProduct(Product product) throws Exception {

		Connection conn = getConnetcion();
		
		int result = dao.manageProduct(conn,product);
		
		if(result >0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
}
