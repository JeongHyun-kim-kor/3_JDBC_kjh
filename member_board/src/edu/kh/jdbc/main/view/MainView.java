package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

// 메인 화면
public class MainView {
	
	Scanner sc = new Scanner(System.in);	
	
	/**
	 * 메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n***** 회원제 게시판 프로그램 *****\n");
				System.out.println("1. 로그인");
				System.out.println("2. 회원 가입");
				System.out.println("0. 프로그램 종료");
				
				System.out.println("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();// 입력 버퍼 개행문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: break;
				case 2: break;
				case 0: System.out.println("프로그램 종료.");break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
				
			}
			
			
			
			
		}while(input !=0);
		
	}
	
}
