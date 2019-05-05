package com.lero.model;

/**
 * @Description : 项目管理者实体
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class ItemManager {
	
	private int itemManagerId;
	private String userName;
	private String password;
	private int itemTypeId;
	private String itemTypeName;
	private String name;
	private String sex;
	private String tel;

	public ItemManager() {
	}

	public ItemManager(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public ItemManager(String userName, String password, 
			String name, String sex, String tel) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
	}

	public int getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(int itemManagerId) {
		this.itemManagerId = itemManagerId;
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
}
