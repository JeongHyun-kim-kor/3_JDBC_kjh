package edu.jdbc.manager.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.jdbc.manager.model.service.ManagerService;
import edu.jdbc.manager.vo.Manager;
import edu.jdbc.product.vo.Product;

public class ManagerView {

	private Scanner sc =new Scanner(System.in);
	private ManagerService service = new ManagerService();
	private Manager loginManager = null;
	private int input = -1;
	
//	public static List <Product> productList = null; // 저장...?
	
	public void checkProduct()  {
		
		this.loginManager = loginManager;
		
		System.out.println("\n[상품 조회]\n");
		
		try {
		List <Product> productList = service.selectAll();
		
		if(productList.isEmpty()) {
			System.out.println("[조회 결과가 없습니다.]");
		}else {
			for(Product p : productList) {
				System.out.printf("상품 번호 : %d | 카테고리 : %s |"
						+ " 상품명 : %s | 재고 수 : %d | 가격 : %d \n" , 
						p.getProductNo(),p.getProductCate(),p.getProductName()
						, p.getProductStock(),p.getProductPrice());
				
			}
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	
}


	/** 상품 수정(가격, 재고수)
	 * 
	 */
	public void manageProduct() {
		try {
		System.out.print("\n[상품 수정]\n");
		
		// 상품 목록 출력
		
		checkProduct();
		
		System.out.print("수정할 상품의 번호를 입력하세요 : \n");
		int input = sc.nextInt();
		
		System.out.print("재고 수 변경 : ");
		int num1 = sc.nextInt();
		System.out.print("가격 변경 : ");
		int num2 = sc.nextInt();
		
		System.out.println();
		// 번호를 입력하고 
		
		Product product = new Product();
		product.setProductNo(input);
		product.setProductStock(num1);
		product.setProductPrice(num2);
		
		int result = service.manageProduct(product);
		
		if(result >0) {
			product.setProductStock(num1);
			product.setProductPrice(num2);
			
			System.out.println("재고, 가격이 수정되었습니다.");
			
			
		} else {
			System.out.println("수정 실패");
		}
		} catch(Exception e) {
			System.out.println("상품 수정 중 예외 발생");
			e.printStackTrace();
		}
	}


	public void addProduct() {

		try {
			System.out.println("\n[상품 추가]\n");
			
			System.out.print("카테고리 명 : ");
			String cate = sc.nextLine();
			System.out.print("상품 명 : ");
			String pName = sc.nextLine();
			System.out.print("재고 수 : ");
			int stock = sc.nextInt();
			System.out.print("가격 : ");
			int price = sc.nextInt();
			
//			Product product = new Product(cate, pName, stock, price);
			
			int result = service.addProduct(cate, pName, stock, price);
			
			if(result > 0) {
				System.out.println("\n[제품이 추가되었습니다.]\n");
			} else {
				System.out.println("추가 실패!");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
