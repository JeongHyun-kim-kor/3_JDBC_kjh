package edu.jdbc.product.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.jdbc.common.JDBCTemplate.*;
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
	// 검색한 제품명의 카테고리와 가격 가져오기
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
	
	
	
}
