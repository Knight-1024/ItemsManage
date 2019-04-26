package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.ItemType;
import com.lero.model.Record;
import com.lero.util.StringUtil;

public class RecordDao {
	public List<Record> recordList(Connection con, Record s_record)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(s_record.getSubprojectNumber())) {
			sb.append(" and t1.subprojectNumber like '%"+s_record.getSubprojectNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_record.getSubprojectName())) {
			sb.append(" and t1.subprojectName like '%"+s_record.getSubprojectName()+"%'");
		}
		if(s_record.getItemTypeId()!=0) {
			sb.append(" and t1.itemTypeId="+s_record.getItemTypeId());
		}
		if(StringUtil.isNotEmpty(s_record.getDate())) {
			sb.append(" and t1.date="+s_record.getDate());
		}
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setSubprojectNumber(rs.getString("subprojectNumber"));
			record.setSubprojectName(rs.getString("subprojectName"));
			int itemTypeId = rs.getInt("itemTypeId");
			record.setItemTypeId(itemTypeId);
			record.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			record.setDeveloperName(rs.getString("developerName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<Record> recordListWithBuild(Connection con, Record s_record, int buildId)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(s_record.getSubprojectNumber())) {
			sb.append(" and t1.subprojectNumber like '%"+s_record.getSubprojectNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_record.getSubprojectName())) {
			sb.append(" and t1.subprojectName like '%"+s_record.getSubprojectName()+"%'");
		}
		sb.append(" and t1.itemTypeId="+buildId);
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setSubprojectNumber(rs.getString("subprojectNumber"));
			record.setSubprojectName(rs.getString("subprojectName"));
			int itemTypeId = rs.getInt("itemTypeId");
			record.setItemTypeId(itemTypeId);
			record.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			record.setDeveloperName(rs.getString("developerName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<Record> recordListWithNumber(Connection con, Record s_record, String subprojectNumber)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(subprojectNumber)) {
			sb.append(" and t1.subprojectNumber ="+subprojectNumber);
		} 
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setSubprojectNumber(rs.getString("subprojectNumber"));
			record.setSubprojectName(rs.getString("subprojectName"));
			int itemTypeId = rs.getInt("itemTypeId");
			record.setItemTypeId(itemTypeId);
			record.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			record.setDeveloperName(rs.getString("developerName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
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
//	
//	public int subprojectCount(Connection con, Subproject s_subproject)throws Exception {
//		StringBuffer sb = new StringBuffer("select count(*) as total from t_subproject t1");
//		if(StringUtil.isNotEmpty(s_subproject.getName())) {
//			sb.append(" and t1.name like '%"+s_subproject.getName()+"%'");
//		} else if(StringUtil.isNotEmpty(s_subproject.getSubNumber())) {
//			sb.append(" and t1.subNum like '%"+s_subproject.getSubNumber()+"%'");
//		} else if(StringUtil.isNotEmpty(s_subproject.getDeveloperName())) {
//			sb.append(" and t1.developerName like '%"+s_subproject.getDeveloperName()+"%'");
//		}
//		if(s_subproject.getItemTypeId()!=0) {
//			sb.append(" and t1.itemTypeId="+s_subproject.getItemTypeId());
//		}
//		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		ResultSet rs = pstmt.executeQuery();
//		if(rs.next()) {
//			return rs.getInt("total");
//		} else {
//			return 0;
//		}
//	}
	
	public Record recordShow(Connection con, String recordId)throws Exception {
		String sql = "select * from t_record t1 where t1.recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		ResultSet rs=pstmt.executeQuery();
		Record record = new Record();
		if(rs.next()) {
			record.setRecordId(rs.getInt("recordId"));
			record.setSubprojectNumber(rs.getString("subprojectNumber"));
			record.setSubprojectName(rs.getString("subprojectName"));
			int itemTypeId = rs.getInt("itemTypeId");
			record.setItemTypeId(itemTypeId);
			record.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			record.setDeveloperName(rs.getString("developerName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
		}
		return record;
	}
	
	public int recordAdd(Connection con, Record record)throws Exception {
		String sql = "insert into t_record values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getSubprojectNumber());
		pstmt.setString(2, record.getSubprojectName());
		pstmt.setInt(3, record.getItemTypeId());
		pstmt.setString(4, record.getDeveloperName());
		pstmt.setString(5, record.getDate());
		pstmt.setString(6, record.getDetail());
		return pstmt.executeUpdate();
	}
	
	public int recordDelete(Connection con, String recordId)throws Exception {
		String sql = "delete from t_record where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		return pstmt.executeUpdate();
	}
	
	public int recordUpdate(Connection con, Record record)throws Exception {
		String sql = "update t_record set subprojectNumber=?,subprojectName=?,itemTypeId=?,developerName=?,detail=? where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getSubprojectNumber());
		pstmt.setString(2, record.getSubprojectName());
		pstmt.setInt(3, record.getItemTypeId());
		pstmt.setString(4, record.getDeveloperName());
		pstmt.setString(5, record.getDetail());
		pstmt.setInt(6, record.getRecordId());
		return pstmt.executeUpdate();
	}
	
	
}
