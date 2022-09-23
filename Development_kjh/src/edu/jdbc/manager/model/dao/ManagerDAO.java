package edu.jdbc.manager.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.jdbc.product.vo.Product;
import static edu.jdbc.common.JDBCTemplate.*;
public class ManagerDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop ;

	public ManagerDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("manager-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public List<Product> selectAll(Connection conn) throws Exception {

		List<Product> resultList = new ArrayList<>();
		
		try {
		String sql = prop.getProperty("selectAll");
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			
			int productNo = rs.getInt("PRODUCT_NO");
			String productCategory = rs.getString("CATEGORY");
			String productName = rs.getString("PRODUCT_NAME");
			int productStock = rs.getInt("STOCK");
			int productPrice = rs.getInt("PRICE");
			
			Product product = new Product();
			product.setProductNo(productNo);
			product.setProductCate(productCategory);
			product.setProductName(productName);
			product.setProductStock(productStock);
			product.setProductPrice(productPrice);
			
			resultList.add(product);
			
		}	
//		PRODUCT_NO, CATEGORY, PRODUCT_NAME, STOCK, PRICE
			
		}finally {
		close(rs);
		close(stmt);
		}
		
		return resultList;
	}

}
