package edu.jdbc.product.vo;

public class Product {

	private int productNo;
	private String productCate;
	private String productName;
	private int productStock;
	private int productPrice;
	private String buyDate;
	private String deletyNy;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	


	public Product(int productNo, String productName, int productStock) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productStock = productStock;
	}




	public Product(int productNo, String productCate, String productName, int productStock, int productPrice,
			String buyDate, String deletyNy) {
		super();
		this.productNo = productNo;
		this.productCate = productCate;
		this.productName = productName;
		this.productStock = productStock;
		this.productPrice = productPrice;
		this.buyDate = buyDate;
		this.deletyNy = deletyNy;
	}




	public Product(int productNo, String productName, int productStock, String buyDate, String deletyNy) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productStock = productStock;
		this.buyDate = buyDate;
		this.deletyNy = deletyNy;
	}




	public String getBuyDate() {
		return buyDate;
	}




	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}




	public String getDeletyNy() {
		return deletyNy;
	}




	public void setDeletyNy(String deletyNy) {
		this.deletyNy = deletyNy;
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
