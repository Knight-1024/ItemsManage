package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.ItemType;
import com.lero.model.ItemManager;
import com.lero.model.PageBean;
import com.lero.util.StringUtil;

public class ItemTypeDao {

	public List<ItemType> itemTypeList(Connection con, PageBean pageBean, ItemType s_itemType)throws Exception {
		List<ItemType> itemTypeList = new ArrayList<ItemType>();
		StringBuffer sb = new StringBuffer("select * from t_itemType t1");
		if(StringUtil.isNotEmpty(s_itemType.getItemTypeName())) {
			sb.append(" where t1.itemTypeName like '%"+s_itemType.getItemTypeName()+"%'");
		}
		if(pageBean != null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ItemType itemType=new ItemType();
			itemType.setItemTypeId(rs.getInt("itemTypeId"));
			itemType.setItemTypeName(rs.getString("itemTypeName"));
			itemType.setDetail(rs.getString("itemTypeDetail"));
			itemTypeList.add(itemType);
		}
		return itemTypeList;
	}
	
	public static String itemTypeName(Connection con, int itemTypeId)throws Exception {
		String sql = "select * from t_itemType where itemTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, itemTypeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString("itemTypeName");
		}
		return null;
	}
	
	public int itemTypeCount(Connection con, ItemType s_itemType)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_itemType t1");
		if(StringUtil.isNotEmpty(s_itemType.getItemTypeName())) {
			sb.append(" where t1.itemTypeName like '%"+s_itemType.getItemTypeName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public ItemType itemTypeShow(Connection con, String itemTypeId)throws Exception {
		String sql = "select * from t_itemType t1 where t1.itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemTypeId);
		ResultSet rs=pstmt.executeQuery();
		ItemType itemType = new ItemType();
		if(rs.next()) {
			itemType.setItemTypeId(rs.getInt("itemTypeId"));
			itemType.setItemTypeName(rs.getString("itemTypeName"));
			itemType.setDetail(rs.getString("itemTypeDetail"));
		}
		return itemType;
	}
	
	public int itemTypeAdd(Connection con, ItemType itemType)throws Exception {
		String sql = "insert into t_itemType values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemType.getItemTypeName());
		pstmt.setString(2, itemType.getDetail());
		return pstmt.executeUpdate();
	}
	
	public int itemTypeDelete(Connection con, String itemTypeId)throws Exception {
		String sql = "delete from t_itemType where itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemTypeId);
		return pstmt.executeUpdate();
	}
	
	public int itemTypeUpdate(Connection con, ItemType itemType)throws Exception {
		String sql = "update t_itemType set itemTypeName=?,itemTypeDetail=? where itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemType.getItemTypeName());
		pstmt.setString(2, itemType.getDetail());
		pstmt.setInt(3, itemType.getItemTypeId());
		return pstmt.executeUpdate();
	}
	
	public boolean existManOrDeveloperWithId(Connection con, String itemTypeId)throws Exception {
		boolean isExist = false;
//		String sql="select * from t_itemType,t_itemManager,t_connection where itemManId=managerId and itemTypeId=buildId and itemTypeId=?";
		String sql = "select *from t_itemManager where itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemTypeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			isExist = true;
		} else {
			isExist = false;
		}
		String sql1="select * from t_itemType t1,t_developer t2 where t1.itemTypeId=t2.itemTypeId and t1.itemTypeId=?";
		PreparedStatement p=con.prepareStatement(sql1);
		p.setString(1, itemTypeId);
		ResultSet r = pstmt.executeQuery();
		if(r.next()) {
			return isExist;
		} else {
			return false;
		}
	}
	
	public List<ItemManager> itemManWithoutBuild(Connection con)throws Exception {
		List<ItemManager> itemManagerList = new ArrayList<ItemManager>();
		String sql = "SELECT * FROM t_itemManager WHERE itemTypeId IS NULL OR itemTypeId=0";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ItemManager itemManager=new ItemManager();
			itemManager.setItemTypeId(rs.getInt("itemTypeId"));
			itemManager.setItemManagerId(rs.getInt("itemManId"));
			itemManager.setName(rs.getString("name"));
			itemManager.setUserName(rs.getString("userName"));
			itemManager.setSex(rs.getString("sex"));
			itemManager.setTel(rs.getString("tel"));
			itemManagerList.add(itemManager);
		}
		return itemManagerList;
	}
	
	public List<ItemManager> itemManWithBuildId(Connection con, String itemTypeId)throws Exception {
		List<ItemManager> itemManagerList = new ArrayList<ItemManager>();
		String sql = "select *from t_itemManager where itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemTypeId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ItemManager itemManager=new ItemManager();
			itemManager.setItemTypeId(rs.getInt("itemTypeId"));
			itemManager.setItemManagerId(rs.getInt("itemManId"));
			itemManager.setName(rs.getString("name"));
			itemManager.setUserName(rs.getString("userName"));
			itemManager.setSex(rs.getString("sex"));
			itemManager.setTel(rs.getString("tel"));
			itemManagerList.add(itemManager);
		}
		return itemManagerList;
	}
	
	public int managerUpdateWithId (Connection con, String itemManagerId, String itemTypeId)throws Exception {
		String sql = "update t_itemManager set itemTypeId=? where itemManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemTypeId);
		pstmt.setString(2, itemManagerId);
		return pstmt.executeUpdate();
	}
}
