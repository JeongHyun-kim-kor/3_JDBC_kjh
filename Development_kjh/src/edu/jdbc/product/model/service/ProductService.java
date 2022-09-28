package edu.jdbc.product.model.service;

import static edu.jdbc.common.JDBCTemplate.*;
import static edu.jdbc.common.JDBCTemplate.close;
import static edu.jdbc.common.JDBCTemplate.commit;
import static edu.jdbc.common.JDBCTemplate.rollback;
import static edu.jdbc.common.JDBCTemplate.close;
import static edu.jdbc.common.JDBCTemplate.commit;
import static edu.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import edu.jdbc.product.model.dao.ProductDAO;
import edu.jdbc.product.vo.Product;

public class ProductService {

	private static ProductDAO dao = new ProductDAO();

	
	
	
	public List<Product> selectAll(int memberNo) throws Exception{

		Connection conn = getConnetcion();
		
		List<Product> productList = dao.selectAll(conn, memberNo);
		
		close(conn);
		
		return productList;
	}




	




	public List<Product> checkBuyProductList(int memberNo, int pMemberNo) throws Exception{

		Connection conn = getConnetcion();
		
		List<Product> productList = dao.checkBuyProductList(conn, memberNo, pMemberNo);
		
		close(conn);
		
		return productList;
	}



	public static  int changeStock(String bp, int count) throws Exception{

		Connection conn = getConnetcion();
		
		int result = dao.changeStock(conn,bp, count);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}









	public int deleteBuyProduct(int memberNo, int input) throws Exception {

		Connection conn = getConnetcion();
		
		int	result = dao.deleteBuyProduct(conn,memberNo,input);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		
		return result;
	}



	
	
	
	
}
