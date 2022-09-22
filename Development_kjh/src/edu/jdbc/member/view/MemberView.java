package edu.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.jdbc.member.vo.Member;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	
	private Member loginMember =null;
	
	private int input = -1;
	
	public void memberMenu(Member loginMember) {

		
		this.loginMember = loginMember;
		
		do {
			try {
				System.out.println("\n ==== 회원 기능 ====");
				System.out.println();
				System.out.println("1. 내 정보 조회");
				System.out.println("0. 메인메뉴로 이동");
		
				System.out.print("메뉴 입력 :");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : selectMyInfo(); break;
				
				case 0 : System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
			
		} while(input !=0);
		
	}

	private void selectMyInfo() {

		System.out.println("[내 정보 조회]");
		
		System.out.println("회원 정보 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.println("성별 : "+ loginMember.getMemberGender());
		System.out.println("가입일 : "+ loginMember.getEnrollDate());
		
	}

	

}
