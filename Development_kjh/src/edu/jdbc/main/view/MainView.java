package edu.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.jdbc.main.model.service.MainService;
import edu.jdbc.member.vo.Member;

public class MainView {

	private MainService service = new MainService();
	
	private Scanner sc = new Scanner(System.in);
	
	public static Member loginMember = null;
	
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				System.out.println("**** 로그인 메뉴 ****");
				System.out.println("1. 로그인");
				System.out.println("2. 회원 가입");
				System.out.println("9. 관리자 로그인");
				System.out.println("0. 프로그램 종료");
				
				System.out.println("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: login(); break; // 로그인  // 기존 회원으로 로그인하는 것
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("메뉴에 알맞는 숫자를 입력해주세요.");
				sc.nextLine();
			}
		} while(input !=0);
		
	}

	private void login() {
		System.out.println("[로그인]");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			loginMember = service.login(memberId, memberPw);
			
			
		}catch(Exception e) {
			System.out.println("<<로그인 중 예외 발생>>");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
