package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.kh.emp.model.vo.Employee;

// DAO(Daga Access Object, 데이터 접근 객체)
// -> 데이터베이스에 접근(연결)하는 객체
// -> JDBC 코드 작성
public class EmployeeDAO {

	// JDBC 객체 참조 변수로 필드 선언(class 내부에서 공통 사용)
	
	private Connection conn;  // 필드(Heap, 변수가 비어있을 수 없음)
	private Statement stmt;   // -> JVM이 지정한 기본값으로 초기화
	private ResultSet rs;	  // -> 참조형의 초기값은 null(별도 초기화 필요없다)
	
	private PreparedStatement pstmt;
	// Statement의 자식으로 향상된 기능을 제공
	// -> ? 기호 사용(placeholder, 위치홀더)를 이용해서
	// 	  SQL에 작성되어지는 리터럴을 동적으로 제어함
	
	// SQL ? 기호에 추가되는 값은 
	// 숫자인 경우 '' 없이 대입
	// 문자열인 경우 ''가 자동으로 추가되어 대입
	
	
	
	
	
	
	
	private String driver ="oracle.jdbc.driver.OracleDriver";
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
					+ " FROM EMPLOYEE "
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



	/** 주민등록번호가 일치하는 사원 정보 조회 DAO
	 * @param empNo
	 * @return emp
	 */
	public Employee selectEmpNo(String empNo) {

		// 결과 저장용 변수 선언
		Employee emp = null;
		
		try {
			// Connection 생성
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			
			// SQL 작성
			String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE,"
					+ "		NVL(DEPT_TITLE, '부서없음') DEPT_TITLE,"
					+ "		JOB_NAME, SALARY"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)"
					+ " JOIN JOB USING (JOB_CODE)"
					+ " WHERE EMP_NO = ? ";  // '621231-1985634' 삭제 -> ? 작성
									// placeholder
			
					// Statement 객체 사용시 순서
					// SQL 작성 -> Statement 생성 -> SQL 수행 후 결과 반환
			
					// PreparedStatement  객체 사용 시 순서
					// SQL 작성 -> 
					// PreparedStatement 객체 생성(?가 포함된 SQL을 매개변수로 사용)
					// -> ?에 알맞은 값 대입
					// -> SQL 수행 후 결과 반환
					
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?에 알맞은 값 대입
			pstmt.setString(1, empNo);
			
			// SQL 수행 후 결과 반환
			rs = pstmt.executeQuery(); // sql대입 X
			// 이미 pstmt에 sql이 적재되어있는 상태
			// PreparedStatement는 객체 생성시 이미 SQL이 담겨져 있는 상태이므로 
			// SQL 수행 시 매개변수로 전달할 필요가 없다.
			
			// -> 실수로 SQL을 매개변수에 추가하면
			// ?에 추가했던 값이 모두 사라져 수행 시 오류 발생
			
			if(rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
//				String empNo = rs.getString("EMP_NO");
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
			
			
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 결과 반환
		return emp;
	}



	/** 사원 정보 추가 DAO
	 * @param emp
	 * @return result(INSERT 성공한 행의 개수 반환)
	 */
	public int insertEmployee(Employee emp) {

		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			
			// 커넥션 생성
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			
			// ** DML 수행할 예정
			// - 트랜잭션에 DML 구문이 임시 저장이 된다.
			// -> 정상적인 DML인지를 판별해서 개발자가 직접 commit, rollback을 수행
			
			// 하지만.. Connection 객체 생성시
			// AutoCommit이 활성화 되어있는 상태이기 때문에 
			// 이를 해제하는 코드를 추가해야 한다.!!!!!!!!!!!!!!!!!!!!!!!!!
			
			conn.setAutoCommit(false); // AutoCommit 비활성화
			// -> AutoCommit 비활성화해도
			// conn.close(); 구문이 수행되면 자동으로 Commit이 수행됨
			// -> close() 수행 전에 트랜잭션 제어코드를 작성해야 함!!
			
			//SQL 작성
			String sql = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE, NULL, DEFAULT)";
			// 퇴사여부 컬럼의 DEFAULT == 'N'
			
			// PreparedStatement 객체 생성 (매개변수에 SQL 추가)
			pstmt = conn.prepareStatement(sql);
			
			// ?(placeholder)에 알맞은 값 대입
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setInt(11, emp.getManagerId());
			
			// SQL수행 후 결과 반환 받기
			
			result = pstmt.executeUpdate();
			//executeUpdate() : DML(INSERT, UPDATE, DELETE) 수행 후 결과 행 개수 반환
			// executeQuery() : SELECT 수행후 ResultSet 반환
			
			// *** 트랜잭션 제어 처리 ***
			// -> DML 성공 여부에 따라서 commit, rollback 제어
			
			if(result>0) conn.commit(); // DML 성공시 commit
		 else conn.rollback();  
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 결과 반환
		return result;
	}



	/** 사번이 일치하는 사원 정보 수정 DAO
	 * @param emp
	 * @return result
	 */
	public int updateEmployee(Employee emp) {
		int result = 0; // 결과 저장용 변수
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			conn.setAutoCommit(false); // AutoCommit 비활성화
			
			String sql = "UPDATE EMPLOYEE SET"
					+ " EMAIL = ?, PHONE = ?, SALARY = ?"
					+ " WHERE EMP_ID = ?";
			
			// PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			
			//  ?에 알맞은 값을 세팅
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setInt(4, emp.getEmpId());
			
			result = pstmt.executeUpdate(); // 반영된 행의 개수 반환
			
			// 트랜잭션 제어처리
			if(result == 0) conn.rollback();
			else 			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				// JDBC 객체 자원 반환
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
				while(rs.next()) {
					
					
					
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return result; // 결과 반환
	}



	/** 5. 사번이 일치하는 사원 정보 삭제
	 * @param empId
	 * @return
	 */
	public int deleteEmployee(int empId) {

		int result = 0;
			
//		Employee emp = new Employee();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate();
			
			if(result >0) conn.commit();
			else conn.rollback();
			
		} catch(Exception e	){
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			
		}
		return result;
	}



	/** 6. 입력받은 부서와 일치하는 모든 사원 정보 조회
	 * @param dept
	 * @return
	 */
	public List<Employee> selectDeptEmp(String dept) {
		
		List <Employee> empList = new ArrayList<>();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pw);
			
			String sql ="SELECT EMP_ID ,EMP_NAME, EMP_NO, EMAIL, PHONE, \r\n"
					+ "NVL(DEPT_TITLE,'부서없음') DEPT_TITLE, JOB_NAME, SALARY \r\n"
					+ "FROM EMPLOYEE \r\n"
					+ "LEFT JOIN DEPARTMENT ON DEPT_CODE = DEPT_ID\r\n"
					+ "JOIN JOB USING(JOB_CODE) \r\n"
					+ "WHERE DEPT_TITLE = '"+dept+"'";
					
					
			
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
				
				Employee emp = new Employee(empId, empName, empNo, email, phone,
						departmentTitle, jobName, salary);
				
				empList.add(emp);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		return empList;
	}



	/** 7. 입력받은 급여 이상을 받는 모든 사원 정보 조회
	 * @param salary
	 * @return
	 */
	public List<Employee> selectSalaryEmp(int salary) {
		
		List<Employee> empList = new ArrayList<>();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			String sql = "SELECT EMP_ID ,EMP_NAME, EMP_NO, EMAIL, PHONE, \r\n"
					+ "NVL(DEPT_TITLE,'부서없음') DEPT_TITLE, JOB_NAME, SALARY \r\n"
					+ "FROM EMPLOYEE \r\n"
					+ "LEFT JOIN DEPARTMENT ON DEPT_CODE = DEPT_ID\r\n"
					+ "JOIN JOB USING(JOB_CODE)\r\n"
					+ "WHERE SALARY > " + salary ;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
//				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, email, phone,
						departmentTitle, jobName, salary);
				
				empList.add(emp);
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
					
			
			try {
				
				if(rs!= null) rs.close();
				if(stmt != null) stmt.close();
				if(conn !=null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return  empList;
	}

	/** 8. 부서별 급여 합 전체 조회
	 * @return
	 */
	public HashMap<String, Integer> selectDeptTotalSalary() {

		HashMap<String, Integer> map = new HashMap<>();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			
			String sql = "SELECT DEPT_TITLE, SUM(SALARY) TOTAL"
					+ " FROM EMPLOYEE "
					+ " JOIN DEPARTMENT ON DEPT_CODE = DEPT_ID"
					+ " GROUP BY DEPT_TITLE ";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String deptName = rs.getString("DEPT_TITLE");
				int total = rs.getInt("TOTAL");
				
				map.put(deptName, total);
				
				
			}
			
			
		} catch(Exception e	) {
			e.printStackTrace();
			
			
		}finally {
			
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		
		
		
		return map;
	}





	/** 10. 직급별 급여 평균 조회
	 * @return
	 */
	public HashMap<String, Integer> selectJobavgSalary() {
		HashMap<String, Integer> map = new HashMap<>();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT JOB_NAME, AVG(SALARY) AVERAGE\r\n"
					+ "FROM EMPLOYEE \r\n"
					+ "LEFT JOIN DEPARTMENT ON DEPT_CODE = DEPT_ID\r\n"
					+ "JOIN JOB USING(JOB_CODE)\r\n"
					+ "GROUP BY JOB_NAME";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String jobName = rs.getString("JOB_NAME");
				int average =  rs.getInt("AVERAGE");
				
				map.put(jobName, average);
			}
			
			
			
			
		} catch (Exception e	) {
			e.printStackTrace();
			
			
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null)stmt.close();
				if(conn != null) conn.close();
				
				
				
			}catch(Exception e	) {
				e.printStackTrace();
			}
		}
		
		return map;
	}



	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
