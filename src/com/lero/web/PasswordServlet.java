package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.UserDao;
import com.lero.model.Admin;
import com.lero.model.Developer;
import com.lero.model.ItemManager;
import com.lero.model.Subproject;
import com.lero.util.DbUtil;

public class PasswordServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new  UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		if("preChange".equals(action)) {
			passwordPreChange(request, response);
			return;
		} else if("change".equals(action)) {
			passwordChange(request, response);
			return;
		}else if("developer".equals(action)){
			developer(request, response);
		}
	}

	private void developer(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Connection con = null;
		try {
			con = dbUtil.getCon();

			Developer developer = (Developer) (session.getAttribute("currentUser"));
			if(userName!=null&&!"".equals(userName)){
				developer.setUserName(userName);
			}
			if(name!=null&&!"".equals(name)){
				developer.setUserName(name);
			}
			if(sex!=null&&!"".equals(sex)){
				developer.setUserName(sex);
			}
			if(tel!=null&&!"".equals(tel)&&isNumeric(tel)){
				developer.setUserName(tel);
			}
			userDao.developerUpdate(con,developer);
			String msg = "";
			if (!"".equals(oldPassword) && oldPassword!=null && oldPassword.equals(developer.getPassword())) {
				userDao.developerUpdate(con, developer.getDeveloperId(), newPassword);
				developer.setPassword(newPassword);
			} else {
				msg = "原密码错误，密码修改失败！";
			}
			request.setAttribute("userName", developer.getUserName());
			request.setAttribute("name", developer.getName());
			request.setAttribute("sex", developer.getSex());
			request.setAttribute("tel", developer.getTel());
			request.setAttribute("oldPassword", oldPassword);
			request.setAttribute("newPassword", newPassword);
			request.setAttribute("rPassword", newPassword);
			request.setAttribute("error", "信息修改成功！"+msg);
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

	private void passwordChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			if("admin".equals((String)currentUserType)) {
				Admin admin = (Admin)(session.getAttribute("currentUser"));
				if(oldPassword.equals(admin.getPassword())) {
					userDao.adminUpdate(con, admin.getAdminId(), newPassword);
					admin.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "admin/passwordChange.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错误");
					request.setAttribute("mainPage", "admin/passwordChange.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				}
			} else if("itemManager".equals((String)currentUserType)) {
				ItemManager manager = (ItemManager)(session.getAttribute("currentUser"));
				if(oldPassword.equals(manager.getPassword())) {
					userDao.adminUpdate(con, manager.getItemManagerId(), newPassword);
					manager.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "itemManager/passwordChange.jsp");
					request.getRequestDispatcher("mainManager.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错误");
					request.setAttribute("mainPage", "itemManager/passwordChange.jsp");
					request.getRequestDispatcher("mainManager.jsp").forward(request, response);
				}
			} else if("subproject".equals((String)currentUserType)) {
				Subproject subproject = (Subproject)(session.getAttribute("currentUser"));
				if(oldPassword.equals(subproject.getPassword())) {
					userDao.adminUpdate(con, subproject.getSubprojectId(), newPassword);
					subproject.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "subproject/passwordChange.jsp");
					request.getRequestDispatcher("mainSubproject.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错误");
					request.setAttribute("mainPage", "subproject/passwordChange.jsp");
					request.getRequestDispatcher("mainSubproject.jsp").forward(request, response);
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
	}

	private void passwordPreChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "admin/passwordChange.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} else if("itemManager".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "itemManager/passwordChange.jsp");
			request.getRequestDispatcher("mainManager.jsp").forward(request, response);
		} else if("developer".equals((String)currentUserType)) {

			Developer developer = (Developer) (session.getAttribute("currentUser"));
			request.setAttribute("userName", developer.getUserName());
			request.setAttribute("name", developer.getName());
			request.setAttribute("sex", developer.getSex());
			request.setAttribute("tel", developer.getTel());
			request.setAttribute("mainPage", "developer/change.jsp");
			request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
		}
	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
}
