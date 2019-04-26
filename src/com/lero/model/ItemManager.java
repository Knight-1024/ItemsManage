package com.lero.model;

public class ItemManager {
	
	private int ItemManagerId;
	private String userName;
	private String password;
	private int itemTypeId;
	private String itemTypeName;
	private String name;
	private String sex;
	private String tel;

	private ItemType itemType;

	public ItemManager() {

	}
	
	public ItemManager(String userName, String password, 
			String name, String sex, String tel) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
	}



	public ItemManager(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public int getItemManagerId() {
		return ItemManagerId;
	}
	public void setItemManagerId(int ItemManagerId) {
		this.ItemManagerId = ItemManagerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
}
