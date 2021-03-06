package com.lero.model;

/**
 * @Description : 任务实体
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class Subproject {
	private int subprojectId;
	private String subNumber;
	private int itemTypeId;
	private String itemTypeName;
	private String developerName;
	private String name;
	private String state;  //0表示未完成，1表示完成
	private String tel;
	private int developerId;

	public Subproject() {
	}

	public Subproject(String subNumber, int itemTypeId,
			String developerId, String name, String state) {
		this.subNumber = subNumber;
		this.itemTypeId = itemTypeId;
		this.developerName = developerId;
		this.name = name;
		this.state = state;
	}

	public int getSubprojectId() {
		return subprojectId;
	}

	public void setSubprojectId(int subprojectId) {
		this.subprojectId = subprojectId;
	}

	public String getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

}
