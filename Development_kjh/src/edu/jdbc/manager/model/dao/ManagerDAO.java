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

	public int manageProduct(Connection conn, Product product) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("manageProduct");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product.getProductStock());
			pstmt.setInt(2, product.getProductPrice());
			pstmt.setInt(3, product.getProductNo());
			
			result =pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result ;
	}

	public int addProduct(Connection conn, String cate, String pName, int stock, int price) throws Exception{

		int result = 0;
		try {
			String sql = prop.getProperty("addProduct");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, pName);
			pstmt.setInt(3, stock);
			pstmt.setInt(4, price);
			result = pstmt.executeUpdate();
			
//			Product product = new Product();
//			product.setProductCate(cate);
//			product.setProductName(pName);
//			product.setProductStock(stock);
//			product.setProductPrice(price);
			
			
			
			
		} finally {
			
			close(pstmt);
			
		}
		
		
		return result;
	}

}
