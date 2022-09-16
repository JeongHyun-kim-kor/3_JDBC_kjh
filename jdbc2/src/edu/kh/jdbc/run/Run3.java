package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run3 {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("번호 입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.print("제목 입력 : ");
		String content1 = sc.nextLine();
		System.out.print("내용 입력 : ");
		String content2 = sc.nextLine();
		
	
		TestVO vo1 = new TestVO(num, content1, content2);
		
		
		
		try {
//			int result = service.update(vo1, num, content1, content2);
			int result = service.update(vo1); // 수정
			
			if(result >0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("실패..");
			}
		}catch(Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			e.printStackTrace();
		}
		
		
		// 번호, 제목, 내용을 입력받아
		// 번호가 일치하는 행의 제목, 내용 수정
		
		// 수정 성공 시 -> 수정되었습니다.
		// 수정 실패 시 -> 일치하는 번호가 없습니다.
		// 예외 발생 시 -> 수정 중 예외가 발생했습니다.
	}
}
