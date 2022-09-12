package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Employee;

public class DAO {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private PreparedStatement pstmt;
	
	private String dirver = "oracle:jdbc:driver:OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "kh_kjh";
	private String pw = "kh1234";
	
	/** 2. 전체 사원조회
	 * @return resultList
	 */
	public List<Employee> selectAll() {
		
		List<Employee> resultList = new ArrayList<>();
		
		try {
			Class.forName(dirver);
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql	);
			
			while(rs.next()) {
				
				
				
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null	) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e	) {
				e.printStackTrace();
			}
		}
		
		
		
		return resultList;
	
		
	}
	
	
}
