package com.lero.model;

/**
 * @Description : 项目类型实体
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class ItemType {
	private int itemTypeId;
	private String itemTypeName;
	private String detail;

	public ItemType() {
	}
	
	public ItemType(String itemTypeName, String detail) {
		this.itemTypeName = itemTypeName;
		this.detail = detail;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
