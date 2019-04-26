package com.lero.model;

public class Subproject {
	private int subprojectId;
	private String subNumber;
	private String userName;
	private String password;
	private int itemTypeId;
	private String itemTypeName;
	private String developerName;
	private String name;
	private String state;
	private String tel;
	private int developerId;

	private ItemType itemType;
	private Developer developer;

	public Subproject() {
	}
	
	public Subproject(String userName, String password) {
		this.subNumber = userName;
		this.userName = userName;
		this.password = password;
	}
	
	
	public Subproject(String subNumber, String password, int itemTypeId,
			String developerName, String name, String state, String tel) {
		this.subNumber = subNumber;
		this.userName = subNumber;
		this.password = password;
		this.itemTypeId = itemTypeId;
		this.developerName = developerName;
		this.name = name;
		this.state = state;
		this.tel = tel;
	}

	public int getSubprojectId() {
		return subprojectId;
	}
	public void setSubprojectId(int subprojectId) {
		this.subprojectId = subprojectId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		this.subNumber = userName;
	}
	public String getSubNumber() {
		return subNumber;
	}
	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
		this.userName = subNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
}
