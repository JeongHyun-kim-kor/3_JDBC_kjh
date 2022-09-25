package edu.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.jdbc.manager.model.service.ManagerService;
import edu.jdbc.manager.view.ManagerView;
import edu.jdbc.member.model.service.MemberService;
import edu.jdbc.member.vo.Member;
//import edu.jdbc.product.model.service.ProductService;
import edu.jdbc.product.vo.Product;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private ManagerService service = new ManagerService();

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
				System.out.println("3. 구매 내역 확인");
				
				
				System.out.println("0. 메인메뉴로 이동");
		
				System.out.print("메뉴 입력 :");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : selectMyInfo(); break;
				case 2 : buyProduct(); break;
				case 3 : checkBuyProduct(); break;
				case 4 : deleteBuyProduct(); break;
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
		
		
		MemberService mService = new MemberService();
//		ProductService pService = new ProductService();
		try {
		System.out.println("\n[제품 구매하기]\n");
		
		ManagerView mv = new ManagerView();
		mv.checkProduct();
		
		
		System.out.println();
		
		System.out.println("구매하실 제품의 카테고리 : ");
		String cate =sc.next();
		
		System.out.print("구매하실 제품명 : ");
		String bp = sc.next();
		
		System.out.print("구매하실 개수 : ");
		int count = sc.nextInt();
		
		System.out.print("구매하는 제품의 가격 : ");
		int price = sc.nextInt();
		
		Product pd = new Product();
		pd.setProductName(bp);
		pd.setProductStock(count);
		pd.setProductCate(cate);
		pd.setProductPrice(price);
		
		int result =  mService.buyProduct(pd);
		List<Product> buyProductList = service.buyProductSelectAll();
		
		if(result>0) {
			System.out.println("구매 성공!!");
			System.out.println("[구매한 제품 내역]");
//			List<Product> productList = pService.selectAll();
			for(Product p : buyProductList) {
				System.out.printf("번호 : %d | 카테고리 : %s |"
				+ " 상품명 : %s | 재고 수 : %d | 가격 : %d \n" , 
				p.getProductNo(),p.getProductCate(),p.getProductName()
				, p.getProductStock(),p.getProductPrice());
				
				System.out.println("======================");
			}
		} else {
			System.out.println("구매 실패!!");
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	

}

	private void checkBuyProduct()  {
		
		System.out.println("\n구매 내역 조회\n");
		
		try {
			List<Product> productList = service.buyProductSelectAll();
			
			if(productList.isEmpty()) {
				System.out.println("[구매 내역이 없습니다.]");
			} else {
				for(Product p : productList) {
					System.out.printf("%d | %s | %s | %d | %d",
					p.getProductNo(),p.getProductCate(), p.getProductName(),
					p.getProductStock(),p.getProductPrice());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
	
	private void deleteBuyProduct() {

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}












