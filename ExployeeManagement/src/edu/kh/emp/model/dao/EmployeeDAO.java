package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.emp.model.vo.Employee;

// DAO(Daga Access Object, 데이터 접근 객체)
// -> 데이터베이스에 접근(연결)하는 객체
// -> JDBC 코드 작성
public class EmployeeDAO {

	// JDBC 객체 참조 변수로 필드 선언(class 내부에서 공통 사용)
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
	private Connection conn;  // 필드(Heap, 변수가 비어있을 수 없음)
	private Statement stmt;   // -> JVM이 지정한 기본값으로 초기화
	private ResultSet rs;	  // -> 참조형의 초기값은 null(별도 초기화 필요없다)
	
	private String url= "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "kh_kjh";
	private String pw = "kh1234";
	
	
	
//	public void method() {
//		Connection conn2; // 지역변수(Stack, 변수가 비어있을 수 있음)
//	}



	/**	전체 사원 정보 조회 DAO
	 * @return empList
	 */
	public List<Employee> selectAll() {
		// 1. 결과 저장용 변수 선언
		List<Employee> empList = new ArrayList<>();
				
		try {
		
			// 2. JDBC참조 변수에 객체 대입
			// == conn, stmt, rs에 객체 대입
			
			Class.forName(driver); // 오라클 jdbc 드라이버 객체 메모리 로드
			conn = DriverManager.getConnection(url, user, pw);
			// 오라클 jdbc 드라이버 객체를 이용하여 DB 접속 방법 생성
			
			String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE,"
					+ "		NVL(DEPT_TITLE, '부서없음') DEPT_TITLE,"
					+ "		JOB_NAME, SALARY"
					+ " FROM EMPLOYEE \r\n"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)";
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL을 수행 후 결과(ResultSet) 반환 받음
			rs = stmt.executeQuery(sql);
			
			// 3. 조회결과를 얻어와 한 행씩 접근하여 
			// Employee객체 생성 후 컬럼 값 옮겨 담기
			// -> List에 추가
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				// EMP_ID 컬럼은 문자열 컬럼이지만 
				// 저장된 값들이 모두 숫자형태
				// -> DB에서 자동으로 형변환진행에서 얻어옴
				
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, email, phone,
						departmentTitle, jobName, salary);
				
				empList.add(emp); // List담기
				
			} // while 종료
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
				// 4. JDBC 객체 자원 반환
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e 	) {
				e.printStackTrace();
			}
		}
		
		
		// 5. 결과 반환
		return empList;
	}



	/** 사번이 일치하는 사원 정보 조회 DAO
	 * @param empId
	 * @return emp
	 */
	public Employee selectEmpId(int empId) {

		// 결과 저장용 변수를 선언
		Employee emp =null; 
		// null로 한 이유? 만약 조회 결과가 있으면 Employee 객체를 생성해서 emp에 대입
		// 				조회 결과가 없으면 emp에 아무것도 대입하지 않음(null)
		
		try {
			
			Class.forName(driver); // 오라클 JDBC 드라이버 메모리 로드
			conn = DriverManager.getConnection(url, user, pw); // 커넥션 생성해서 얻어오기
			
			//SQL 작성
			String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE,"
					+ "		NVL(DEPT_TITLE, '부서없음') DEPT_TITLE,"
					+ "		JOB_NAME, SALARY\r\n"
					+ " FROM EMPLOYEE \r\n"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)"
					+ " WHERE EMP_ID = '"+empId+"'";
					//  WHERE EMP_ID = " + empId ; 도 가능
			
			// Statement 생성
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과(ResultSet) 바환 받기
			rs = stmt.executeQuery(sql);
			
			// ** 조회결과가 최대 1행인 경우 while 대신 if문
			// 불필요한 조건 검사를 줄이기 위해
			
			if(rs.next()) { // 조회 결과가 있을 경우
				
//				int empId = rs.getInt("EMP_ID"); > 파라미터가 같은 값이므로 불필요
				// EMP_ID 컬럼은 문자열 컬럼이지만 
				// 저장된 값들이 모두 숫자형태
				// -> DB에서 자동으로 형변환진행에서 얻어옴
				
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				// 조회 결과를 담은 Employee 객체 생성 후
				// 결과 저장용 변수 emp에 대입
				emp =new Employee(empId, empName, empNo, email,
						phone, departmentTitle, jobName, salary);
				
				
				
			}
			
			
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		// 결과 반환
		return emp;
	}
	
	
	
}
