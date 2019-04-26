package com.lero.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	
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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String userType = request.getParameter("userType");
		
		Connection con = null;
		try {
			con=dbUtil.getCon();
			Admin currentAdmin = null;
			ItemManager currentItemManager = null;
			Developer currentDeveloper = null;
			if("admin".equals(userType)) {
				Admin admin = new Admin(userName, password);
				currentAdmin = userDao.Login(con, admin);
				if(currentAdmin == null) {
					request.setAttribute("admin", admin);
					request.setAttribute("error", "�˺Ż��������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if("remember-me".equals(remember)) {
						rememberMe(userName, password, userType,response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "admin");
					session.setAttribute("currentUser", currentAdmin);
					request.setAttribute("mainPage", "admin/blank.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				}
			} else if("itemManager".equals(userType)) {
				ItemManager itemManager = new ItemManager(userName, password);
				currentItemManager = userDao.Login(con, itemManager);
				if(currentItemManager == null) {
					request.setAttribute("itemManager", itemManager);
					request.setAttribute("error", "�˺Ż��������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if("remember-me".equals(remember)) {
						rememberMe(userName, password, userType,response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "itemManager");
					session.setAttribute("currentUser", currentItemManager);
					request.setAttribute("mainPage", "itemManager/blank.jsp");
					request.getRequestDispatcher("mainManager.jsp").forward(request, response);
				}
			} else if("developer".equals(userType)) {
				Developer developer = new Developer(userName, password);
				currentDeveloper = userDao.Login(con, developer);
				if(currentDeveloper == null) {
					request.setAttribute("developer", developer);
					request.setAttribute("error", "�˺Ż��������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if("remember-me".equals(remember)) {
						rememberMe(userName, password, userType,response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "developer");
					session.setAttribute("currentUser", currentDeveloper);
					request.setAttribute("mainPage", "developer/blank.jsp");
					request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void rememberMe(String userName, String password, String userType, HttpServletResponse response) {
		Cookie user = new Cookie("loginuser", userName+"-"+password+"-"+userType+"-"+"yes");
		user.setMaxAge(1*60*60*24*7);
		response.addCookie(user);
	}
	
	private void deleteCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("loginuser")){
				if(userName.equals(userName=cookies[i].getValue().split("-")[0])) {
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}
