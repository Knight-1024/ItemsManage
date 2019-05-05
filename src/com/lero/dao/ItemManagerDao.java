package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.ItemManager;
import com.lero.model.PageBean;
import com.lero.util.StringUtil;

/**
 * @Description : 项目管理者访问层
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class ItemManagerDao {

	public List<ItemManager> itemManagerList(Connection con, PageBean pageBean, ItemManager s_itemManager)throws Exception {
		List<ItemManager> itemManagerList = new ArrayList<ItemManager>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_itemmanager t1");
		if(s_itemManager!=null && StringUtil.isNotEmpty(s_itemManager.getName())) {
			sb.append(" where t1.name like '%"+s_itemManager.getName()+"%'");
		} else if(s_itemManager!=null && StringUtil.isNotEmpty(s_itemManager.getUserName())) {
			sb.append(" where t1.userName like '%"+s_itemManager.getUserName()+"%'");
		}
		if(pageBean != null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ItemManager itemManager=new ItemManager();
			itemManager.setItemManagerId(rs.getInt("itemManId"));
			int itemTypeId = rs.getInt("itemTypeId");
			itemManager.setItemTypeId(itemTypeId);
			itemManager.setItemTypeName(ItemTypeDao.itemTypeName(con, itemTypeId));
			itemManager.setName(rs.getString("name"));
			itemManager.setSex(rs.getString("sex"));
			itemManager.setUserName(rs.getString("userName"));
			itemManager.setTel(rs.getString("tel"));
			itemManager.setPassword(rs.getString("password"));
			itemManagerList.add(itemManager);
		}
		return itemManagerList;
	}
	
	public int itemManagerCount(Connection con, ItemManager s_ItemManager)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_itemmanager t1");
		if(StringUtil.isNotEmpty(s_ItemManager.getName())) {
			sb.append(" where t1.name like '%"+s_ItemManager.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_ItemManager.getUserName())) {
			sb.append(" where t1.userName like '%"+s_ItemManager.getUserName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public ItemManager itemManagerShow(Connection con, String ItemManagerId)throws Exception {
		String sql = "select * from t_itemmanager t1 where t1.itemManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ItemManagerId);
		ResultSet rs=pstmt.executeQuery();
		ItemManager itemManager = new ItemManager();
		if(rs.next()) {
			itemManager.setItemManagerId(rs.getInt("itemManId"));
			itemManager.setItemTypeId(rs.getInt("itemTypeId"));
			itemManager.setName(rs.getString("name"));
			itemManager.setSex(rs.getString("sex"));
			itemManager.setUserName(rs.getString("userName"));
			itemManager.setTel(rs.getString("tel"));
			itemManager.setPassword(rs.getString("password"));
		}
		return itemManager;
	}
	
	public int itemManagerAdd(Connection con, ItemManager ItemManager)throws Exception {
		String sql = "insert into t_itemmanager values(null,?,?,null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ItemManager.getUserName());
		pstmt.setString(2, ItemManager.getPassword());
		pstmt.setString(3, ItemManager.getName());
		pstmt.setString(4, ItemManager.getSex());
		pstmt.setString(5, ItemManager.getTel());
		return pstmt.executeUpdate();
	}
	
	public int itemManagerDelete(Connection con, String ItemManagerId)throws Exception {
		String sql = "delete from t_itemmanager where itemManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ItemManagerId);
		return pstmt.executeUpdate();
	}
	
	public int itemManagerUpdate(Connection con, ItemManager itemManager)throws Exception {
		String sql = "update t_itemmanager set userName=?,password=?,name=?,sex=?,tel=? where itemManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, itemManager.getUserName());
		pstmt.setString(2, itemManager.getPassword());
		pstmt.setString(3, itemManager.getName());
		pstmt.setString(4, itemManager.getSex());
		pstmt.setString(5, itemManager.getTel());
		pstmt.setInt(6, itemManager.getItemManagerId());
		return pstmt.executeUpdate();
	}

	public boolean haveManagerByUser(Connection con, String userName) throws Exception {
		String sql = "select * from t_itemmanager t1 where t1.userName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userName);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
}
