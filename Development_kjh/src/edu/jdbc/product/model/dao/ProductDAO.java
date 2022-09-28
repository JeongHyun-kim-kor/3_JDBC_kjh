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
	public List<Product> selectAll(Connection conn, int memberNo) throws Exception {

		List<Product> resultList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAll");
					
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
//				Product product = new Product(rs.getInt("BUY_NO"),
////									rs.getString("CATEGORY"),
//									rs.getString("PRODUCT_NAME"),
//									rs.getInt("STOCK"),
////									rs.getInt("PRICE"),
//									rs.getString("BUYDATE"));
////									rs.getString("DELETE_NY"));
		int buyNo = rs.getInt("BUY_NO");
		String productname = rs.getString("PRODUCT_NAME");
		int productStock = rs.getInt("STOCK");
		String buyDate = rs.getString("BUYDATE");
				
		Product product = new Product();
		product.setBuyNo(buyNo);
		product.setProductName(productname);
		product.setProductStock(productStock);
		product.setBuyDate(buyDate);
		
					resultList.add(product);
			}
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return resultList;
	}

	

	public List<Product> checkBuyProductList(Connection conn,int memberNo,int pMemberNo) throws Exception{

		List<Product> productList = new ArrayList<>();
		Product product = null;
		
		try {
			String sql = prop.getProperty("checkBuyProductList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				product = new Product();
				
				product.setProductNo(rs.getInt("PRODUCT_NO"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setProductStock(rs.getInt("STOCK"));
				
				productList.add(product);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}

	public int changeStock(Connection conn, String bp, int count) throws Exception {
		
		int result = 0;
		try {
			String sql = prop.getProperty("changeStock");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,	count);
			pstmt.setString(2, bp);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int deleteBuyProduct(Connection conn, int memberNo, int input) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("deleteBuyProduct");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
