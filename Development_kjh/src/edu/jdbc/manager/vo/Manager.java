package edu.jdbc.manager.vo;

public class Manager {

	private int managerNo;
	private String managerId;
	private String managerPw;
	private String managerName;
	private String managerGender;
	private String enrollDate;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}
	

	public Manager(int managerNo, String managerId, String managerName, String managerGender, String enrollDate) {
		super();
		this.managerNo = managerNo;
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerGender = managerGender;
		this.enrollDate = enrollDate;
	}


	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerGender() {
		return managerGender;
	}

	public void setManagerGender(String managerGender) {
		this.managerGender = managerGender;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
}
