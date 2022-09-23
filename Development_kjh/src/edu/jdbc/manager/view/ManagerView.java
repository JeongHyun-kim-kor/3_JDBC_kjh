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
	
	
	public void checkProduct(Manager loginManager)  {
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
