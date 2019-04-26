package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lero.model.Admin;
import com.lero.model.Developer;
import com.lero.model.ItemManager;
import com.lero.model.Subproject;

public class UserDao {

	public Admin Login(Connection con, Admin admin)throws Exception {
		Admin resultAdmin = null;
		String sql = "select * from t_admin where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, admin.getUserName());
		pstmt.setString(2, admin.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultAdmin = new Admin();
			resultAdmin.setAdminId(rs.getInt("adminId"));
			resultAdmin.setUserName(rs.getString("userName"));
			resultAdmin.setPassword(rs.getString("password"));
			resultAdmin.setName(rs.getString("name"));
			resultAdmin.setSex(rs.getString("sex"));
			resultAdmin.setTel(rs.getString("tel"));
		}
		return resultAdmin;
	}
	
	public ItemManager Login(Connection con, ItemManager itemManager)throws Exception {
		ItemManager resultItemManager = null;
		String sql = "select * from t_itemmanager where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, itemManager.getUserName());
		pstmt.setString(2, itemManager.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultItemManager = new ItemManager();
			resultItemManager.setItemManagerId(rs.getInt("itemManId"));
			resultItemManager.setUserName(rs.getString("userName"));
			resultItemManager.setPassword(rs.getString("password"));
			resultItemManager.setItemTypeId(rs.getInt("itemTypeId"));
			resultItemManager.setName(rs.getString("name"));
			resultItemManager.setSex(rs.getString("sex"));
			resultItemManager.setTel(rs.getString("tel"));
		}
		return resultItemManager;
	}

	public Developer Login(Connection con, Developer developer)throws Exception {
		Developer resultDeveloper = null;
		String sql = "select * from t_developer where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, developer.getUserName());
		pstmt.setString(2, developer.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultDeveloper = new Developer();
			resultDeveloper.setDeveloperId(rs.getInt("developerId"));
			resultDeveloper.setUserName(rs.getString("userName"));
			resultDeveloper.setPassword(rs.getString("password"));
			resultDeveloper.setItemManaerId(rs.getInt("itemManaerId"));
			resultDeveloper.setName(rs.getString("name"));
			resultDeveloper.setSex(rs.getString("sex"));
			resultDeveloper.setTel(rs.getString("tel"));
		}
		return resultDeveloper;
	}
	
	public int adminUpdate(Connection con, int adminId, String password)throws Exception {
		String sql = "update t_admin set password=? where adminId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, adminId);
		return pstmt.executeUpdate();
	}
	
	public int managerUpdate(Connection con, int managerId, String password)throws Exception {
		String sql = "update t_developermanager set password=? where itemManId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, managerId);
		return pstmt.executeUpdate();
	}

	public int developerUpdate(Connection con, int developerId, String password)throws Exception {
		String sql = "update t_developer set password=? where developerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, developerId);
		return pstmt.executeUpdate();
	}

	public int developerUpdate(Connection con, Developer developer)throws Exception {
		String sql = "update t_developer set userName=?, name=?, sex=?, tel=? where developerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, developer.getUserName());
		pstmt.setString(2, developer.getName());
		pstmt.setString(3, developer.getSex());
		pstmt.setString(4, developer.getTel());
		pstmt.setInt(5, developer.getDeveloperId());
		return pstmt.executeUpdate();
	}

}
