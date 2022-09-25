package edu.jdbc.product.model.dao;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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
	
	
	
}
