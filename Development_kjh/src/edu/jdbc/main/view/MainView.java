package edu.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.jdbc.main.model.service.MainService;
import edu.jdbc.manager.view.ManagerView;
import edu.jdbc.manager.vo.Manager;
import edu.jdbc.member.view.MemberView;
import edu.jdbc.member.vo.Member;

public class MainView {

	private MainService service = new MainService();
	
	public static Member loginMember = null;
	private MemberView memberView = new MemberView();

	private Scanner sc = new Scanner(System.in);
	
	
	public static Manager loginManager = null;
	public static ManagerView managerView = new ManagerView();
	
	public void mainMenu(){
		
		int input = -1;
		
		do {
			try {
				if(loginMember == null && loginManager == null) {
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
				case 2 : signUp(); break;
				case 9 : managerLogin(); break; // 매니저 로그인
				
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				}else if(loginMember != null){
					System.out.println("==== 사용자 로그인 메뉴 ====");
					System.out.println("1. 회원 기능");
					System.out.println("0. 로그아웃");
					
					System.out.print("\n 메뉴 선택 : ");
					input = sc.nextInt();
					System.out.println();
					
					
					switch(input) {
					
					case 1 : memberView.memberMenu(loginMember); break;
					
					
					}
					
					
					
				} else if(loginManager != null) {
					System.out.println("==== 관리자 로그인 메뉴 ====");
					System.out.println("1. 상품 확인");
					System.out.println("2. 재고 관리");
					System.out.println("3. 상품 추가");
					System.out.print("\n 메뉴 선택 : ");
					int mInput = sc.nextInt();
					System.out.println();
					
					switch(mInput) {
					
					case 1 : managerView.checkProduct(); break;
					case 2 : managerView.manageProduct(); break;
					case 3 : managerView.addProduct(); break;
					}
				}
			
				} catch(InputMismatchException e) {
				System.out.println("입력 형식이 올바르지 않습니다.");
				sc.nextLine();
			} 
				
			
			
		} while(input !=0);
		
	}

	private void signUp() {
		System.out.println("[회원 가입]");
		
		String memberId = null;
		
		try {
			while(true) {
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				// 아이디 중복 검사
				int result = service.idDupCheck(memberId);
				System.out.println();
				
				
			}
			
			
		} catch(Exception e	) {
			e.printStackTrace();
			System.out.println("회원 가입 중 예외 발생");
		}
	}



	private void login() {
		System.out.println("[회원 로그인]");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			loginMember = service.login(memberId, memberPw);
			System.out.println();

			
			if(loginMember != null) {
				System.out.println(loginMember.getMemberName()+"님. 환영합니다.");
			}else {
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
			
		}catch(Exception e) {
			System.out.println("<<회원 로그인 중 예외 발생>>");
			e.printStackTrace();
		}
		
	}
	
	
	private void managerLogin() {
		System.out.println("[관리자 로그인]");
		
		System.out.print("아이디 : ");
		String managerId = sc.next();
		
		System.out.print("비밀번호 : ");
		String managerPw = sc.next();
		
		try {
			loginManager = service.managerLogin(managerId, managerPw);
			
			if(loginManager != null) {
				System.out.println(loginManager.getManagerName()+"님 환영합니다. 관리하실 메뉴를 선택해주세요.");
			} else {
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");

			}
			
		} catch(Exception e) {
			System.out.println("<관리자 로그인 중 예외 발생>");
			e.printStackTrace();
		}
	}
	
	
	

	
	
	
	
}
