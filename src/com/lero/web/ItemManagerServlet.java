package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.ItemManagerDao;
import com.lero.model.ItemManager;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

public class ItemManagerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	ItemManagerDao itemManagerDao = new ItemManagerDao();
	
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
		String s_itemManagerText = request.getParameter("s_itemManagerText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		ItemManager itemManager = new ItemManager();
		if("preSave".equals(action)) {
			itemManagerPreSave(request, response);
			return;
		} else if("save".equals(action)){
			itemManagerSave(request, response);
			return;
		} else if("delete".equals(action)){
			itemManagerDelete(request, response);
			return;
		} else 
			if("list".equals(action)) {
			if(StringUtil.isNotEmpty(s_itemManagerText)) {
				if("name".equals(searchType)) {
					itemManager.setName(s_itemManagerText);
				} else if("userName".equals(searchType)) {
					itemManager.setUserName(s_itemManagerText);
				}
			}
			session.removeAttribute("s_itemManagerText");
			session.removeAttribute("searchType");
			request.setAttribute("s_itemManagerText", s_itemManagerText);
			request.setAttribute("searchType", searchType);
		} else if("search".equals(action)){
			if (StringUtil.isNotEmpty(s_itemManagerText)) {
				if ("name".equals(searchType)) {
					itemManager.setName(s_itemManagerText);
				} else if ("userName".equals(searchType)) {
					itemManager.setUserName(s_itemManagerText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_itemManagerText", s_itemManagerText);
			} else {
				session.removeAttribute("s_itemManagerText");
				session.removeAttribute("searchType");
			}
		} else {
			if(StringUtil.isNotEmpty(s_itemManagerText)) {
				if("name".equals(searchType)) {
					itemManager.setName(s_itemManagerText);
				} else if("userName".equals(searchType)) {
					itemManager.setUserName(s_itemManagerText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_itemManagerText", s_itemManagerText);
			}
			if(StringUtil.isEmpty(s_itemManagerText)) {
				Object o1 = session.getAttribute("s_itemManagerText");
				Object o2 = session.getAttribute("searchType");
				if(o1!=null) {
					if("name".equals((String)o2)) {
						itemManager.setName((String)o1);
					} else if("userName".equals((String)o2)) {
						itemManager.setUserName((String)o1);
					}
				}
			}
		}
		if(StringUtil.isEmpty(page)) {
			page="1";
		}
		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		request.setAttribute("pageSize", pageBean.getPageSize());
		request.setAttribute("page", pageBean.getPage());
		try {
			con=dbUtil.getCon();
			List<ItemManager> itemManagerList = itemManagerDao.itemManagerList(con, pageBean, itemManager);
			int total=itemManagerDao.itemManagerCount(con, itemManager);
			String pageCode = this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("itemManagerList", itemManagerList);
			request.setAttribute("mainPage", "admin/itemManager.jsp");
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

	private void itemManagerDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String itemManagerId = request.getParameter("itemManagerId");
		Connection con = null;
			try {
			con = dbUtil.getCon();
			itemManagerDao.itemManagerDelete(con, itemManagerId);
			request.getRequestDispatcher("itemManager?action=list").forward(request, response);
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

	private void itemManagerSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String itemManagerId = request.getParameter("itemManagerId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		ItemManager itemManager = new ItemManager(userName, password, name, sex, tel);
		if(StringUtil.isNotEmpty(itemManagerId)) {
			itemManager.setItemManagerId(Integer.parseInt(itemManagerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if(StringUtil.isNotEmpty(itemManagerId)) {
				saveNum = itemManagerDao.itemManagerUpdate(con, itemManager);
			} else if(itemManagerDao.haveManagerByUser(con, itemManager.getUserName())){
				request.setAttribute("itemManager", itemManager);
				request.setAttribute("error", "该用户名已存在");
				request.setAttribute("mainPage", "admin/itemManagerSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = itemManagerDao.itemManagerAdd(con, itemManager);
			}
			if(saveNum > 0) {
				request.getRequestDispatcher("itemManager?action=list").forward(request, response);
			} else {
				request.setAttribute("itemManager", itemManager);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "itemManager/itemManagerSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
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

	private void itemManagerPreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String itemManagerId = request.getParameter("itemManagerId");
		if(StringUtil.isNotEmpty(itemManagerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				ItemManager itemManager = itemManagerDao.itemManagerShow(con, itemManagerId);
				request.setAttribute("itemManager", itemManager);
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
		request.setAttribute("mainPage", "admin/itemManagerSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}

	private String genPagation(int totalNum, int currentPage, int pageSize){
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='itemManager?page=1'>首页</a></li>");
		if(currentPage==1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}else {
			pageCode.append("<li><a href='itemManager?page="+(currentPage-1)+"'>上一页</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++) {
			if(i<1||i>totalPage) {
				continue;
			}
			if(i==currentPage) {
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			} else {
				pageCode.append("<li><a href='itemManager?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='itemManager?page="+(currentPage+1)+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='itemManager?page="+totalPage+"'>尾页</a></li>");
		return pageCode.toString();
	}
	
}
