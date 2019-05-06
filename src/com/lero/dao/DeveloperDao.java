package com.lero.dao;

import com.lero.model.Developer;
import com.lero.model.ItemManager;
import com.lero.model.PageBean;
import com.lero.model.Subproject;
import com.lero.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 开发者数据访问层
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class DeveloperDao {
    ItemManagerDao itemManagerDao = new ItemManagerDao();

    public List<Developer> developerList(Connection con, PageBean pageBean, Developer s_developer)throws Exception {
        List<Developer> developerList = new ArrayList<Developer>();
        StringBuffer sb = new StringBuffer("SELECT * FROM t_developer t1");
        if(s_developer!= null && StringUtil.isNotEmpty(s_developer.getName())) {
            sb.append(" where t1.name like '%"+s_developer.getName()+"%'");
        } else if(s_developer!= null && StringUtil.isNotEmpty(s_developer.getUserName())) {
            sb.append(" where t1.userName like '%"+s_developer.getUserName()+"%'");
        }
        if(pageBean != null) {
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            Developer developer=new Developer();
            developer.setDeveloperId(rs.getInt("developerId"));
            int itemManaerId = rs.getInt("itemManaerId");
            //关联itemManaerId
            ItemManager itemManager = itemManagerDao.itemManagerShow(con, "" + itemManaerId);
            if(itemManager!=null){
                developer.setItemManagerName(itemManager.getName());
            }
            developer.setItemManaerId(itemManaerId);
            developer.setName(rs.getString("name"));
            developer.setSex(rs.getString("sex"));
            developer.setUserName(rs.getString("userName"));
            developer.setTel(rs.getString("tel"));
            developer.setPassword(rs.getString("password"));
            developerList.add(developer);
        }
        return developerList;
    }

    public Developer developerShow(Connection con, String developerId)throws Exception {
        String sql = "select * from t_developer t1 where t1.developerId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, developerId);
        ResultSet rs=pstmt.executeQuery();
        Developer developer = new Developer();
        if(rs.next()) {
            developer.setDeveloperId(rs.getInt("developerId"));
            developer.setItemManaerId(rs.getInt("itemManaerId"));
            developer.setName(rs.getString("name"));
            developer.setSex(rs.getString("sex"));
            developer.setUserName(rs.getString("userName"));
            developer.setTel(rs.getString("tel"));
            developer.setPassword(rs.getString("password"));
        }
        return developer;
    }

    public int developerAdd(Connection con, Developer developer)throws Exception {
        String sql = "insert into t_developer values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, developer.getUserName());
        pstmt.setString(2, developer.getPassword());
        pstmt.setString(3, developer.getItemManaerId().toString());
        pstmt.setString(4, developer.getName());
        pstmt.setString(5, developer.getSex());
        pstmt.setString(6, developer.getTel());
        return pstmt.executeUpdate();
    }

    public int developerDelete(Connection con, String developerId)throws Exception {
        String sql = "delete from t_developer where developerId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, developerId);
        return pstmt.executeUpdate();
    }

    public int developerUpdate(Connection con, Developer developer)throws Exception {
        String sql = "update t_developer set userName=?,name=?,sex=?,tel=? ,itemManaerId=?where developerId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, developer.getUserName());
        pstmt.setString(2, developer.getName());
        pstmt.setString(3, developer.getSex());
        pstmt.setString(4, developer.getTel());
        pstmt.setString(5, developer.getItemManaerId().toString());
        pstmt.setInt(6, developer.getDeveloperId());
        return pstmt.executeUpdate();
    }

    public List<Subproject> showMyList(Connection con,int developerId) throws Exception {
        List<Subproject> subprojectList = new ArrayList<Subproject>();
        String sql = "select * from t_subproject t1 where developerId = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,developerId);
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
            //任务与开发者关联
            Developer developer = developerShow(con,""+developerId);
            if(developer!=null){
                subproject.setDeveloperName(developer.getName());
                subproject.setTel(developer.getTel());
            }
            subprojectList.add(subproject);
        }
        return subprojectList;
    }
}
