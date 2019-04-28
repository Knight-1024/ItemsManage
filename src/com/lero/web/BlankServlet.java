package com.lero.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description : »¶Ó­Ò³¿ØÖÆ
 * @Author : ³ÂºêÐË
 * @data : 2019/3/28
 */
public class BlankServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "admin/blank.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} else if("itemManager".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "itemManager/blank.jsp");
			request.getRequestDispatcher("mainManager.jsp").forward(request, response);
		}else if("developer".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "developer/blank.jsp");
			request.getRequestDispatcher("mainDeveloper.jsp").forward(request, response);
		}
	}
	
}
