package edu.jdbc.member.view;

import java.security.Provider.Service;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.jdbc.manager.view.ManagerView;
import edu.jdbc.member.model.service.MemberService;
import edu.jdbc.member.vo.Member;
import edu.jdbc.product.vo.Product;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	
	private Member loginMember =null;
	
	private int input = -1;
	
	public void memberMenu(Member loginMember) {

//	private MemberService service = new MemberService();
		
		this.loginMember = loginMember;
		
		do {
			try {
				System.out.println("\n ==== 회원 기능 ====");
				System.out.println();
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 구매하기");
				
				System.out.println("0. 메인메뉴로 이동");
		
				System.out.print("메뉴 입력 :");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : selectMyInfo(); break;
				case 2 : buyProduct(); break;
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
	
	private void buyProduct() {
		
		MemberService service = new MemberService();
		try {
		System.out.println("\n[제품 구매하기]\n");
		
//		managerView.checkProduct();
//		ManagerView mv = new ManagerView();
//		mv.checkProduct();
//		
		
		System.out.println();
		
		
		
		System.out.print("구매하실 제품명 : ");
		String bp = sc.nextLine();
		
		System.out.print("구매하실 개수 : ");
		int count = sc.nextInt();
		
		Product pd = new Product();
		pd.setProductName(bp);
		pd.setProductStock(count);
		
		int result =  service.buyProduct(pd);
		
		if(result>0) {
			System.out.println("구매 성공!!");
			
			
		} else {
			System.out.println("구매 실패!!");
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	

}

	
}