package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.vo.Member;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	private Member loginMember = null;
	
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("<<<<< 회원제 게시판 프로그램 >>>>>");
				System.out.println("1. 로그인");
				System.out.println("2. 회원 가입");
				System.out.println("0. 프로그램 종료");
				System.out.println();
				
				
				
				switch(input) {
					case 1: login(); break;
					case 2: signUp(); break;
					case 0 : System.out.println("프로그램 종료"); break;
					default: System.out.println("메뉴판에 작성된 번호를 입력해주세요");
				}	
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				
			
			} catch(InputMismatchException e	) {
				System.out.println("정확한 숫자를 입력해 주십시오");
				e.printStackTrace();
			}
		}while(input !=0);
		}


	
	private void login() {
		System.out.println("[로그인]");
			System.out.print("아이디 : ");
			String memberId = sc.next();
			System.out.print("비밀번호 : ");
			String memberPw = sc.next();
	
		try {
			loginMember = service.login(memberId, memberPw);
			
			if(loginMember != null) { // 로그인 성공시 
				System.out.println(loginMember.getMemberName()+"님 환영합니다.");
				
			} else {
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
		} // try문 종료
		catch(Exception e) {
			System.out.println("<로그인 중 예외 발생>");
			e.printStackTrace();
		}
			
			
	} // login 메서드
	
	
	
	/**
	 * 회원 가입 화면
	 */
	private void signUp() {
		System.out.println("[회원 가입]");
		
		String memberId = null;
		
		String memberPw1= null;
		String memberPw2 = null;
		
		String memberName = null;
		String memberGender = null;
		
		try {
			// 아이디를 입력 받아 중복이 아닐 때까지 반복
			while(true) {
				
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				int result = service.idDupCheck(memberId);
				
				if(result ==0) { //중복되는 것이 없다!
					System.out.println("[사용 가능한 아이디입니다.");
					break; // while문 종료
				} else { //중복인경우
					System.out.println("[이미 사용중인 아이디 입니다.]");
				}
				
			} // while문
		} // try문
			catch()
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	

