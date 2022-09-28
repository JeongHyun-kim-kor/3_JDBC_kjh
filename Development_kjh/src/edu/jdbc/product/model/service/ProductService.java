package edu.jdbc.product.model.service;

import static edu.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import edu.jdbc.product.model.dao.ProductDAO;
import edu.jdbc.product.vo.Product;

public class ProductService {

	private ProductDAO dao = new ProductDAO();

	
	
	
	public List<Product> selectAll() throws Exception{

		Connection conn = getConnetcion();
		
		List<Product> productList = dao.selectAll(conn);
		
		close(conn);
		
		return productList;
	}




	public int changeStock(int count) throws Exception{

		Connection conn = getConnetcion();
		
		int result = dao.changeStock(conn, count);
		
		if(result>0) commit(conn);
		else 		rollback(conn);
		
		return result;
	}
	
	
	
	
}
