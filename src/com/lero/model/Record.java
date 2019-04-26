package com.lero.model;


public class Record {
	
	private int recordId;
	private String subprojectNumber;
	private String subprojectName;
	private String date;
	private String detail;
	private int itemTypeId;
	private String itemTypeName;
	private String developerName;
	private String startDate;
	private String endDate;
	private int developerId;

	private ItemType itemType;
	private Developer developer;
	
	public Record() {
	}
	
	public Record(String subprojectNumber, String date, String detail) {
		this.subprojectNumber = subprojectNumber;
		this.date = date;
		this.detail = detail;
	}
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getSubprojectNumber() {
		return subprojectNumber;
	}
	public void setSubprojectNumber(String subprojectNumber) {
		this.subprojectNumber = subprojectNumber;
	}
	public String getSubprojectName() {
		return subprojectName;
	}
	public void setSubprojectName(String subprojectName) {
		this.subprojectName = subprojectName;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
}
