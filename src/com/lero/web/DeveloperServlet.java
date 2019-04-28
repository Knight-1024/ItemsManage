package com.lero.web;

import com.lero.dao.DeveloperDao;
import com.lero.dao.UserDao;
import com.lero.model.Developer;
import com.lero.model.Subproject;
import com.lero.util.DbUtil;
import com.lero.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @Description : 开发者控制
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class DeveloperServlet  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    DeveloperDao developerDao = new DeveloperDao();

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
        String action = request.getParameter("action");

        if("sublist".equals(action)){
            //可领取任务列表
            sublist(request,response);
            return;
        }else if("preDraw".equals(action)){
            //领取操作
            preDraw(request,response);
            return;
        }else if("change".equals(action)){
            //修改个人信息
            change(request,response);
            return;
        }

    }

    /**
     * 显示可领取列表
     *
     * @param request
     * @param response
     */
    private void sublist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Subproject> subprojectList = developerDao.showSubList(con);
            request.setAttribute("subprojectList", subprojectList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("mainPage", "developer/subproject.jsp");
        request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
    }

    /**
     * 领取操作
     *
     * @param request
     * @param response
     */
    private void preDraw(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO 关联id
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Subproject> subprojectList = developerDao.showSubList(con);
            request.setAttribute("subprojectList", subprojectList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("msg","领取成功");
        sublist(request,response);
    }

    /**
     * 修改个人信息
     *
     * @param request
     * @param response
     */
    private void change(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");
        Connection con = null;
        try {
            con = dbUtil.getCon();

            Developer developer = (Developer) (session.getAttribute("currentUser"));
            if (userName != null && !"".equals(userName)) {
                developer.setUserName(userName);
            }
            if (name != null && !"".equals(name)) {
                developer.setName(name);
            }
            if (sex != null && !"".equals(sex)) {
                developer.setSex(sex);
            }
            if (tel != null && !"".equals(tel) && StringUtil.isNumeric(tel)) {
                developer.setTel(tel);
            }
            userDao.developerUpdate(con, developer);
            request.setAttribute("userName", developer.getUserName());
            request.setAttribute("name", developer.getName());
            request.setAttribute("sex", developer.getSex());
            request.setAttribute("tel", developer.getTel());
            request.setAttribute("error", "信息修改成功！");
            request.setAttribute("mainPage", "developer/change.jsp");
            request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
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

}
