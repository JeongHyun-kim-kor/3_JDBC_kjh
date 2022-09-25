package edu.jdbc.member.model.dao;

import static edu.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import edu.jdbc.manager.model.service.ManagerService;
import edu.jdbc.product.vo.Product;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private Properties prop;
	
	private ManagerService mService = null;
	
	
	public MemberDAO() {
		try { 
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public int buyProduct(Connection conn, Product pd) throws Exception {
		Product p = new Product();
//		List <Product> productList = mService.selectAll();
		
//		p.setProductCate("dd");
//		p.setProductPrice(123);
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("buyProduct");
			//카테고리/ 제품명, 개수 /가격
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ); // 다른 테이블에 있는 값
			pstmt.setString(2, pd.getProductName()); // 입력
			pstmt.setInt(3, pd.getProductStock()); // 입력
			pstmt.setInt(4, );
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
