package edu.jdbc.product.model.dao;

import static edu.jdbc.common.JDBCTemplate.close;
import static edu.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.jdbc.product.vo.Product;

public class ProductDAO {

	
	private Statement stmt;	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop ;
	
	public ProductDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("product-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	/** 구매내역 가져오기
	 * @param bp
	 * @param conn
	 * @return	resultList
	 * @throws Exception
	 */
	public List<Product> selectCatePrice(String bp,Connection conn) throws Exception {
		
		List<Product> resultList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectCatePrice");
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			
			if(rs.next()) {
				String cate = rs.getString("CATEGORY");
				int price = rs.getInt("PRICE");
				
				Product product = new Product();
				product.setProductCate(cate);
				product.setProductPrice(price);
				
				resultList.add(product);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return resultList;
	}
	public List<Product> selectAll(Connection conn) throws Exception {

		List<Product> resultList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAll");
					
			pstmt =  conn.prepareStatement(sql);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(rs.getInt("PRODUCT_NO"),
//									rs.getString("CATEGORY"),
									rs.getString("PRODUCT_NAME"),
									rs.getInt("STOCK"));
//									rs.getInt("PRICE"),
//									rs.getString("BUYDATE"),
//									rs.getString("DELETE_NY"));
					
					resultList.add(product);
			}
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return resultList;
	}

	public int changeStock(Connection conn, int count) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("changeStock");
			pstmt = conn.prepareStatement(sql);
			
			
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
