package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.ItemType;
import com.lero.model.Subproject;
import com.lero.util.StringUtil;

public class SubprojectDao {
	
	public List<Subproject> subprojectList(Connection con, Subproject s_subproject)throws Exception {
		List<Subproject> subprojectList = new ArrayList<Subproject>();
		StringBuffer sb = new StringBuffer("select * from t_subproject t1");
		if(StringUtil.isNotEmpty(s_subproject.getName())) {
			sb.append(" and t1.name like '%"+s_subproject.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getSubNumber())) {
			sb.append(" and t1.subNum like '%"+s_subproject.getSubNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getDeveloperName())) {
			sb.append(" and t1.developerName like '%"+s_subproject.getDeveloperName()+"%'");
		}
		if(s_subproject.getItemTypeId()!=0) {
			sb.append(" and t1.itemTypeId="+s_subproject.getItemTypeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Subproject subproject=new Subproject();
			subproject.setSubprojectId(rs.getInt("subprojectId"));
			int itemTypeId = rs.getInt("itemTypeId");
			subproject.setItemTypeId(itemTypeId);
			subproject.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			subproject.setDeveloperName(rs.getString("developerName"));
			subproject.setName(rs.getString("name"));
			subproject.setState(rs.getString("state"));
			subproject.setSubNumber(rs.getString("subNum"));
			subproject.setTel(rs.getString("tel"));
			subproject.setPassword(rs.getString("password"));
			subprojectList.add(subproject);
		}
		return subprojectList;
	}
	
	public static Subproject getNameById(Connection con, String subprojectNumber, int itemTypeId)throws Exception {
		String sql = "select * from t_subproject t1 where t1.subNum=? and t1.itemTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subprojectNumber);
		pstmt.setInt(2, itemTypeId);
		ResultSet rs=pstmt.executeQuery();
		Subproject subproject = new Subproject();
		if(rs.next()) {
			subproject.setName(rs.getString("name"));
			subproject.setItemTypeId(rs.getInt("itemTypeId"));
			subproject.setDeveloperName(rs.getString("developerName"));
		}
		return subproject;
	}
	
	public boolean haveNameByNumber(Connection con, String subprojectNumber)throws Exception {
		String sql = "select * from t_subproject t1 where t1.subNum=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subprojectNumber);
		ResultSet rs=pstmt.executeQuery();
		Subproject subproject = new Subproject();
		if(rs.next()) {
			subproject.setName(rs.getString("name"));
			subproject.setItemTypeId(rs.getInt("itemTypeId"));
			subproject.setDeveloperName(rs.getString("developerName"));
			return true;
		}
		return false;
	}
	
	public List<Subproject> subprojectListWithBuild(Connection con, Subproject s_subproject, int buildId)throws Exception {
		List<Subproject> subprojectList = new ArrayList<Subproject>();
		StringBuffer sb = new StringBuffer("select * from t_subproject t1");
		if(StringUtil.isNotEmpty(s_subproject.getName())) {
			sb.append(" and t1.name like '%"+s_subproject.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getSubNumber())) {
			sb.append(" and t1.subNum like '%"+s_subproject.getSubNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getDeveloperName())) {
			sb.append(" and t1.developerName like '%"+s_subproject.getDeveloperName()+"%'");
		}
		sb.append(" and t1.itemTypeId="+buildId);
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Subproject subproject=new Subproject();
			subproject.setSubprojectId(rs.getInt("subprojectId"));
			int itemTypeId = rs.getInt("itemTypeId");
			subproject.setItemTypeId(itemTypeId);
			subproject.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			subproject.setDeveloperName(rs.getString("developerName"));
			subproject.setName(rs.getString("name"));
			subproject.setState(rs.getString("state"));
			subproject.setSubNumber(rs.getString("subNum"));
			subproject.setTel(rs.getString("tel"));
			subproject.setPassword(rs.getString("password"));
			subprojectList.add(subproject);
		}
		return subprojectList;
	}
	
	public List<ItemType> itemTypeList(Connection con)throws Exception {
		List<ItemType> itemTypeList = new ArrayList<ItemType>();
		String sql = "select * from t_itemType";
		PreparedStatement pstmt = con.prepareStatement(sql);
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
	
	public int subprojectCount(Connection con, Subproject s_subproject)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_subproject t1");
		if(StringUtil.isNotEmpty(s_subproject.getName())) {
			sb.append(" and t1.name like '%"+s_subproject.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getSubNumber())) {
			sb.append(" and t1.subNum like '%"+s_subproject.getSubNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_subproject.getDeveloperName())) {
			sb.append(" and t1.developerName like '%"+s_subproject.getDeveloperName()+"%'");
		}
		if(s_subproject.getItemTypeId()!=0) {
			sb.append(" and t1.itemTypeId="+s_subproject.getItemTypeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public Subproject subprojectShow(Connection con, String subprojectId)throws Exception {
		String sql = "select * from t_subproject t1 where t1.subprojectId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subprojectId);
		ResultSet rs=pstmt.executeQuery();
		Subproject subproject = new Subproject();
		if(rs.next()) {
			subproject.setSubprojectId(rs.getInt("subprojectId"));
			int itemTypeId = rs.getInt("itemTypeId");
			subproject.setItemTypeId(itemTypeId);
			subproject.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			subproject.setDeveloperName(rs.getString("developerName"));
			subproject.setName(rs.getString("name"));
			subproject.setState(rs.getString("state"));
			subproject.setSubNumber(rs.getString("subNum"));
			subproject.setTel(rs.getString("tel"));
			subproject.setPassword(rs.getString("password"));
		}
		return subproject;
	}
	
	public int subprojectAdd(Connection con, Subproject subproject)throws Exception {
		String sql = "insert into t_subproject values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subproject.getSubNumber());
		pstmt.setString(2, subproject.getPassword());
		pstmt.setString(3, subproject.getName());
		pstmt.setInt(4, subproject.getItemTypeId());
		pstmt.setString(5, subproject.getDeveloperName());
		pstmt.setString(6, subproject.getState());
		pstmt.setString(7, subproject.getTel());
		return pstmt.executeUpdate();
	}
	
	public int subprojectDelete(Connection con, String subprojectId)throws Exception {
		String sql = "delete from t_subproject where subprojectId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subprojectId);
		return pstmt.executeUpdate();
	}
	
	public int subprojectUpdate(Connection con, Subproject subproject)throws Exception {
		String sql = "update t_subproject set subNum=?,password=?,name=?,itemTypeId=?,developerName=?,state=?,tel=? where subprojectId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, subproject.getSubNumber());
		pstmt.setString(2, subproject.getPassword());
		pstmt.setString(3, subproject.getName());
		pstmt.setInt(4, subproject.getItemTypeId());
		pstmt.setString(5, subproject.getDeveloperName());
		pstmt.setString(6, subproject.getState());
		pstmt.setString(7, subproject.getTel());
		pstmt.setInt(8, subproject.getSubprojectId());
		return pstmt.executeUpdate();
	}
}
