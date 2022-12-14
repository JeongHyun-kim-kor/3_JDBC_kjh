package edu.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.jdbc.main.view.MainView;
import edu.jdbc.manager.model.service.ManagerService;
import edu.jdbc.manager.view.ManagerView;
import edu.jdbc.member.model.service.MemberService;
import edu.jdbc.member.vo.Member;
import edu.jdbc.product.model.service.ProductService;
//import edu.jdbc.product.model.service.ProductService;
import edu.jdbc.product.vo.Product;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private ManagerService ManagerService = new ManagerService();
	private MemberService memberService = new MemberService();
	private Member loginMember =null;
	private ProductService pService = new ProductService();
	private int input = -1;
	
	public void memberMenu(Member loginMember) {

	
		
//	private MemberService service = new MemberService();
		
		this.loginMember = loginMember;
		
		do {
			try {
				System.out.println("\n ==== 회원 기능 ====");
				System.out.println();
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 비밀번호 변경");
				System.out.println("3. 구매하기"); 
				System.out.println("4. 구매 내역 삭제"); // >> X
				System.out.println("5. 탈퇴하기");
				
				
				System.out.println("0. 메인메뉴로 이동");
		
				System.out.print("메뉴 입력 :");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : selectMyInfo(); break;
				case 2 : updatePw(); break;
				case 3 : buyProduct(); break; // + 구매내역, 제품 재고 감소
				case 4 : deleteBuyProduct(); break;
				case 5 : unregister(); break; // 탈퇴
				case 0 : System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
			
		} while(input !=0);
		
	}

	private void deleteBuyProduct() {

		System.out.println("\n[구매 내역 삭제 ]\n");
		
		try {
			
		
		List<Product> BuyList = pService.selectAll(loginMember.getMemberNo());
//		System.out.println("구매한 회원 번호 : "+ loginMember.getMemberNo()); // buyProduct테이블에도 동일한 값 입력
		System.out.println("===========================  현재  ============================");
		System.out.println("=========================== 구매내역 ===========================");
		for(Product p : BuyList) {
			System.out.printf
			("번호 : %d |  상품명 : %s | 구매 개수 : %d  | 구매 날짜 : %s", 
			p.getbuyNo(),p.getProductName(), p.getProductStock(), p.getBuyDate());
			System.out.println();
		}
		System.out.print("삭제할 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		
		int result = pService.deleteBuyProduct(loginMember.getMemberNo(), input);
		
		if(result >0) {
			System.out.println("[삭제 완료]");
		} else {
			System.out.println("삭제 실패하였습니다.");
		}
	
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("<<구매 내역 삭제중 예외 발생>>");
	}
	}
	private void unregister() {
		
	System.out.println("\n[회원 탈퇴]\n");
	
	try {
		System.out.print("비밀번호 확인 : ");
		String memberPw = sc.next();
		
		while(true) {
			
			System.out.print("정말 탈퇴하시겠습니까?(Y/N)");
			char ch =sc.next().toUpperCase().charAt(0);
			
			if(ch =='Y') { 						// 로그인된 사람의 비밀번호 / 회원번호
				int result = memberService.unregister(memberPw, loginMember.getMemberNo());
				
				if(result >0) {
					System.out.println("[탈퇴되었습니다.]");
					input = 0; // 메인 메뉴로 이동
					MainView.loginMember = null;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
				break;
			} else if(ch =='N') {
				System.out.println("[취소되었습니다.]");
				break;
			} else {
				System.out.println("\n[Y 또는 N을 입력해주세요.]\n");
			}
			
		}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n<회원 탈퇴 중 예외 발생>\n");
		}
		
	}

	private void updatePw() {

		System.out.println("\n[비밀번호 변경]\n");
		
		try {
			System.out.print("현재 비밀번호 :");
			String currentPw = sc.next();
			
			String newPw1 = null; // 새 비밀번호
			String newPw2 = null; // 새 비밀번호 확인
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next();
				System.out.print("새 비밀번호 확인 : ");
				newPw2 = sc.next();
				
				if(newPw1.equals(newPw2)) {
					break;
				} else {
					System.out.println("새 비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				}			
			}
			int result = memberService.updatePw(currentPw, newPw1,loginMember.getMemberNo() );
															// 로그인된 회원 번호
			
			if(result >0 ) {
				System.out.println("[비밀번호가 변경되었습니다.]");
			}else {
				System.out.println("<비밀번호 변경 실패!!>");
			}
		} catch(Exception e) {
			System.out.println("비밀번호 변경 중 예외 발생, MemberView");
			e.printStackTrace();
		}
		
	}

	private void selectMyInfo() {

		System.out.println("[내 정보 조회]");
		
		System.out.println("회원 정보 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.println("성별 : "+ loginMember.getMemberGender());
		System.out.println("휴대폰 번호 : "+ loginMember.getPhone());
		System.out.println("이메일 : "+ loginMember.getEmail());
		System.out.println("가입일 : "+ loginMember.getEnrollDate());
		
	}
	
	private void buyProduct() {
		
		
		try {
		System.out.println("\n[제품 구매하기]\n");
		ManagerView mv = new ManagerView();
		mv.checkProduct();
		System.out.println();
		
		//
		
		System.out.print("구매하실 제품명 : ");
		String bp = sc.next();
		
		System.out.print("구매하실 개수 : ");
		int count = sc.nextInt();
		

		
		Product pd = new Product();
		pd.setProductName(bp);
		pd.setProductStock(count);
		
		
		int result = memberService.buyProduct(pd, loginMember.getMemberNo());

		if(result>0) {
			//구매 성공시 상품에 있는 재고 수량 감소 (원래 재고량 - 구매량) 0미만은 안되게끔 해야함
			int result2 = ProductService.changeStock(bp, count);

//			pd.setProductName(bp);
//			pd.setProductStock(count);
			
//			System.out.println("pd에 있는 회원번호 값? : "+ pd.getMemberNo());//null
			System.out.println("구매 성공!!"); // 제품명, 수량만 출력
			
			List<Product> BuyList = pService.selectAll(loginMember.getMemberNo());
			System.out.println("구매한 회원 번호 : "+ loginMember.getMemberNo()); // buyProduct테이블에도 동일한 값 입력
			System.out.println("=========================== 구매내역 ===========================");
			for(Product p : BuyList) {
				System.out.printf
				("번호 : %d |  상품명 : %s | 구매 개수 : %d  | 구매 날짜 : %s", 
				p.getbuyNo(),p.getProductName(), p.getProductStock(), p.getBuyDate());
				System.out.println();
			}
				System.out.println("=============================================================");
			
			} else {
			System.out.println("구매 실패!!");
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	}

}
	
	
		
	
	

	
	
	
	
	
	
} // 끝
