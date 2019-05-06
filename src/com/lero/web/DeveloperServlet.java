package com.lero.web;

import com.lero.dao.DeveloperDao;
import com.lero.dao.ItemManagerDao;
import com.lero.dao.SubprojectDao;
import com.lero.dao.UserDao;
import com.lero.model.Developer;
import com.lero.model.ItemManager;
import com.lero.model.Subproject;
import com.lero.util.DbUtil;
import com.lero.util.MD5Util;
import com.lero.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
    ItemManagerDao itemManagerDao = new ItemManagerDao();
    SubprojectDao subprojectDao = new SubprojectDao();

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
            //完成记录
            sublist(request,response);
            return;
        }else if("preDraw".equals(action)){
            //取消完成
            preDraw(request,response);
            return;
        } else if ("preChange".equals(action)) {
            //进入个人信息修改
            preChange(request, response);
            return;
        } else if ("change".equals(action)) {
            //修改个人信息
            change(request, response);
            return;
        }else if("mySublist".equals(action)){
            //我的任务
            mySublist(request,response);
            return;
        }else if("submit".equals(action)){
            //提交
            submit(request,response);
            return;
        }else if ("list".equals(action)){
            //admin查看开发者列表
            adList(request,response);
            return;
        }else if("delete".equals(action)){
            //admin删除开发者
            delete(request,response);
            return;
        }else if("preSave".equals(action)){
            //admin添加或编辑开发者
            preSave(request,response);
            return;
        }else if("save".equals(action)){
            //admin保存开发者
            save(request,response);
            return;
        }else if("reset".equals(action)){
            //admin重置开发者密码
            reset(request,response);
            return;
        }

    }

    /**
     * admin显示开发者列表
     *
     * @param request
     * @param response
     */
    private void adList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Developer> developerList = developerDao.developerList(con, null, null);
            request.setAttribute("developerList", developerList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("mainPage", "admin/developer.jsp");
        request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
    }

    /**
     * admin删除开发者
     *
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String developerId = request.getParameter("developerId");
        String error = "删除失败";
        try {
            con = dbUtil.getCon();
            int i = developerDao.developerDelete(con, developerId);
            if (1==i){
                error = "删除成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("error",error);
        adList(request,response);
    }

    /**
     * admin添加或编辑开发者
     *
     * @param request
     * @param response
     */
    private void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String developerId =  request.getParameter("developerId");
        try {
            con = dbUtil.getCon();
            if (StringUtil.isNotEmpty(developerId)){
                Developer developer = developerDao.developerShow(con, developerId);
                request.setAttribute("developer",developer);
            }
            List<ItemManager> itemManagerList = itemManagerDao.itemManagerList(con, null, null);
            request.setAttribute("itemManagerList",itemManagerList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("mainPage", "admin/developerSave.jsp");
        request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
    }

    /**
     * admin保存开发者
     *
     * @param request
     * @param response
     */
    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String error = null;
        String developerId = request.getParameter("developerId");
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String itemManaerId = request.getParameter("itemManaerId");
        String tel = request.getParameter("tel");
        Developer developer = new Developer(null,userName,null,name,sex,tel);

        if(StringUtil.isNotEmpty(developerId) && StringUtil.isNumeric(developerId)){
            developer.setDeveloperId(Integer.parseInt(developerId));
        }
        if(StringUtil.isNotEmpty(itemManaerId) && StringUtil.isNumeric(itemManaerId)){
            developer.setItemManaerId(Integer.parseInt(itemManaerId));
        }
        try {
            con = dbUtil.getCon();
            List<ItemManager> itemManagerList = itemManagerDao.itemManagerList(con, null, null);
            request.setAttribute("itemManagerList",itemManagerList);
            //空值判断
            if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(name) || StringUtil.isEmpty(sex)
                    || StringUtil.isEmpty(itemManaerId) || StringUtil.isEmpty(tel)){
                request.setAttribute("error","信息填写不完整");
                request.setAttribute("developer",developer);
                request.setAttribute("mainPage", "admin/developerSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            }
            Developer show = developerDao.developerShow(con, developerId);
            if(show.getDeveloperId()!=null){
                int i = developerDao.developerUpdate(con, developer);
                if(i==1){
                    error = "更新成功";
                }else {
                    error = "更新失败";
                }
            }else {
                developer.setPassword("123");//默认密码123
                int i = developerDao.developerAdd(con, developer);
                if(i==1){
                    error = "添加成功";
                }else {
                    error = "添加失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("error",error);
        request.setAttribute("developer",developer);
        request.setAttribute("mainPage", "admin/developerSave.jsp");
        request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
    }

    /**
     * admin重置开发者密码
     *
     * @param request
     * @param response
     */
    private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String error = "重置失败";
        String developerId = request.getParameter("developerId");
        try {
            con = dbUtil.getCon();
            if(StringUtil.isNotEmpty(developerId)){
                Developer developer = developerDao.developerShow(con, developerId);
                if(developer!=null){
                    developer.setPassword(MD5Util.EncoderPwdByMD5("111"));
                    int i = developerDao.developerUpdate(con, developer);
                    if(i==1){
                        error = developer.getName()+"的密码已重置成功";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("error",error);
        adList(request,response);
    }

    /**
     * 显示已完成列表
     *
     * @param request
     * @param response
     */
    private void sublist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Developer developer = (Developer) (session.getAttribute("currentUser"));
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Subproject> showMyList = developerDao.showMyList(con,developer.getDeveloperId());
            List<Subproject> subprojectList = new ArrayList<>();
            if(showMyList!=null && showMyList.size()>0){
                for(Subproject sb : showMyList){
                    if("1".equals(sb.getState())){
                        subprojectList.add(sb);
                    }
                }
            }
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
     * 取消完成操作
     *
     * @param request
     * @param response
     */
    private void preDraw(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subprojectId = request.getParameter("subprojectId");
        Connection con = null;
        String error = "取消失败";
        try {
            con = dbUtil.getCon();
            Subproject subproject = subprojectDao.subprojectShow(con, subprojectId);
            if(subproject!=null){
                subproject.setState("0");
                int i = subprojectDao.subprojectUpdate(con, subproject);
                if(1==i){
                    error = "取消成功";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("error",error);
        sublist(request,response);
    }

    /**
     * 显示已领取列表
     *
     * @param request
     * @param response
     */
    private void mySublist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Developer developer = (Developer) (session.getAttribute("currentUser"));
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Subproject> showMyList = developerDao.showMyList(con,developer.getDeveloperId());
            List<Subproject> subprojectList = new ArrayList<>();
            if(showMyList!=null && showMyList.size()>0){
                for(Subproject sb : showMyList){
                    if(!"1".equals(sb.getState())){
                        subprojectList.add(sb);
                    }
                }
            }
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
        request.setAttribute("mainPage", "developer/mySublist.jsp");
        request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
    }

    /**
     * 提交操作
     *
     * @param request
     * @param response
     */
    private void submit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subprojectId = request.getParameter("subprojectId");
        Connection con = null;
        String error = "提交失败";
        try {
            con = dbUtil.getCon();
            Subproject subproject = subprojectDao.subprojectShow(con, subprojectId);
            if(subproject!=null){
                subproject.setState("1");
                int i = subprojectDao.subprojectUpdate(con, subproject);
                if(1==i){
                    error = "提交成功";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("error",error);
        mySublist(request,response);
    }

    /**
     * 进入个人信息修改
     * @param request
     * @param response
     */
    private void preChange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        Developer developer = (Developer) (session.getAttribute("currentUser"));

        request.setAttribute("userName", developer.getUserName());
        request.setAttribute("name", developer.getName());
        request.setAttribute("sex", developer.getSex());
        request.setAttribute("tel", developer.getTel());
        request.setAttribute("mainPage", "developer/change.jsp");
        request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
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
