package edu.jdbc.product.vo;

public class Product {

	private int productNo;
	private String productCate;
	private String productName;
	private int productStock;
	private int productPrice;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	


	// 카테고리 , 가격
	public Product(String productCate, int productPrice) {
		super();
		this.productCate = productCate;
		this.productPrice = productPrice;
	}








	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductCate() {
		return productCate;
	}

	public void setProductCate(String productCate) {
		this.productCate = productCate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
