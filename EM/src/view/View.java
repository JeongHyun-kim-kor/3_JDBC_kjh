package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DAO;
import model.vo.Employee;

public class View {

	private Scanner sc = new Scanner(System.in);
	
	private DAO dao = new DAO();
	
	
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 새로운 사원 정보 추가");
				System.out.println("2. 전체 사원 정보 조회");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				System.out.println("8. 부서별 급여 합 전체 조회");
				
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				System.out.println("10. 직급별 급여 평균 조회");
				
				System.out.println("0. 프로그램 종료");
				System.out.println();
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				System.out.println();
				
				switch(input) {
//					case 1: insertEmployee(); break;
					case 2: selectAll(); break;
					case 3: selectEmpId(); break;
//					case 4: updateEmployee(); break; // 이메일, 전화번호, 급여
//					case 5: deleteEmployee(); break;
//					case 6: selectDeptEmp(); break;
//					case 7: selectSalaryEmp(); break;
//					case 8: selectDeptTotalSalary(); break; 
//					case 9: selectEmpNo(); break;
//					case 10: selectJobAvgSalary(); break;
					case 0: System.out.println("프로그램을 종료합니다 ... "); break;
					default : System.out.println("메뉴에 존재하는 번호만 입력하세요. ");
				}
				} catch(InputMismatchException e) {
					System.out.println("정수만 입력해주세요.");
					input = -1; // 잘못입력시 프로그램 종료되는 상황 방지
					sc.nextLine();
				}
				
			}while(input !=0);
		}
	
	/** 전달받은 List 모두 출력
	 * @param empList
	 */
	public void printAll(List<Employee> empList) {
		
	      if(empList.isEmpty()) {
	          System.out.println("조회된 사원 정보가 없습니다.");
	          
	       } else {
	          System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
	          System.out.println("------------------------------------------------------------------------------------------------");
	          for(Employee emp : empList) { 
	             System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
	                   emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
	                   emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
	          }
	       }
		
	}
	/** 사원 1명 정보 출력
	 * @param emp
	 */
	public void printOne(Employee emp) {
		
		if(emp == null) {
	          System.out.println("조회된 사원 정보가 없습니다.");
	          
	       } else {
	          System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
	          System.out.println("------------------------------------------------------------------------------------------------");
	             System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
	                   emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
	                   emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
	          
	       }
		
	}	
	
		/**
		 * 2. 전체 사원조회
		 */
		public void selectAll() {
			System.out.println("[전체 사원 정보 조회]");
			
			List <Employee> empList = dao.selectAll();
			
			printAll(empList);
			
		}
	
	
		/** 3. 사번이 일치하는 사원 정보 조회
		 * 
		 */
		public void selectEmpId() {
			
			System.out.println("[사번이 일치하는 사원 정보 조회]");
			
			System.out.print("사번 입력 : ");
			
			int empId = sc.nextInt();
			sc.nextLine();
			
			Employee emp = dao.selectEmpId(empId);
			
			printOne(emp);
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
