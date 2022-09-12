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
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private String url= "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "kh_kjh";
	private String pw = "kh1234";
	
	/** 2. 전체 사원조회
	 * @return resultList
	 */
	public List<Employee> selectAll() {
		
		List<Employee> resultList = new ArrayList<>();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE,"
					+ "		NVL(DEPT_TITLE, '부서없음') DEPT_TITLE,"
					+ "		JOB_NAME, SALARY"
					+ " FROM EMPLOYEE \r\n"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, departmentTitle, jobName, salary);
				
				resultList.add(emp);
				
				
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
