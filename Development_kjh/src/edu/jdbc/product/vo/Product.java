package edu.jdbc.product.vo;

public class Product {

	private int buyNo;
	private String productCate;
	private String productName;
	private int productStock;
	private int productPrice;
	private String buyDate;
	private String deleteNy;
	private int memberNo; // 구매내역용 회원번호
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	


	public Product(int buyNo, String productName, int productStock, String buyDate) {
		super();
		this.buyNo = buyNo;
		this.productName = productName;
		this.productStock = productStock;
		this.buyDate = buyDate;
	}




	public int getbuyNo() {
		return buyNo;
	}




	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}




	public String getDeleteNy() {
		return deleteNy;
	}




	public void setDeleteNy(String deleteNy) {
		this.deleteNy = deleteNy;
	}




	public int getMemberNo() {
		return memberNo;
	}




	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}




	public Product(int buyNo, String productName, int productStock) {
		super();
		this.buyNo = buyNo;
		this.productName = productName;
		this.productStock = productStock;
	}




	public Product(int buyNo, String productCate, String productName, int productStock, int productPrice,
			String buyDate, String deletyNy) {
		super();
		this.buyNo = buyNo;
		this.productCate = productCate;
		this.productName = productName;
		this.productStock = productStock;
		this.productPrice = productPrice;
		this.buyDate = buyDate;
		this.deleteNy = deletyNy;
		
	}




	public Product(int buyNo, String productName, int productStock, String buyDate, String deletyNy) {
		super();
		this.buyNo = buyNo;
		this.productName = productName;
		this.productStock = productStock;
		this.buyDate = buyDate;
		this.deleteNy = deletyNy;
	}




	public String getBuyDate() {
		return buyDate;
	}




	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}




	public String getDeletyNy() {
		return deleteNy;
	}




	public void setDeletyNy(String deletyNy) {
		this.deleteNy = deletyNy;
	}




	public int getProductNo() {
		return buyNo;
	}

	public void setProductNo(int productNo) {
		this.buyNo = productNo;
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
