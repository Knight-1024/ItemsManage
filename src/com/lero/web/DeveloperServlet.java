package com.lero.web;

import com.lero.dao.ItemTypeDao;
import com.lero.model.ItemType;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DeveloperServlet  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DbUtil dbUtil = new DbUtil();
    ItemTypeDao itemTypeDao = new ItemTypeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        String action = request.getParameter("action");
        ItemType itemType = new ItemType();

        if(StringUtil.isEmpty(page)) {
            page="1";
        }
        Connection con = null;
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        request.setAttribute("pageSize", pageBean.getPageSize());
        request.setAttribute("page", pageBean.getPage());
        try {
            con=dbUtil.getCon();
            List<ItemType> itemTypeList = itemTypeDao.itemTypeList(con, pageBean, itemType);
            int total=itemTypeDao.itemTypeCount(con, itemType);
            String pageCode = this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
            request.setAttribute("pageCode", pageCode);
            request.setAttribute("itemTypeList", itemTypeList);
            request.setAttribute("mainPage", "admin/itemType.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String genPagation(int totalNum, int currentPage, int pageSize){
        int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='itemType?page=1'>首页</a></li>");
        if(currentPage==1) {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else {
            pageCode.append("<li><a href='itemType?page="+(currentPage-1)+"'>上一页</a></li>");
        }
        for(int i=currentPage-2;i<=currentPage+2;i++) {
            if(i<1||i>totalPage) {
                continue;
            }
            if(i==currentPage) {
                pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
            } else {
                pageCode.append("<li><a href='itemType?page="+i+"'>"+i+"</a></li>");
            }
        }
        if(currentPage==totalPage) {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        } else {
            pageCode.append("<li><a href='itemType?page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("<li><a href='itemType?page="+totalPage+"'>尾页</a></li>");
        return pageCode.toString();
    }

}
