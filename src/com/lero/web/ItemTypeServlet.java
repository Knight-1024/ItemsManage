package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.ItemTypeDao;
import com.lero.model.ItemType;
import com.lero.model.ItemManager;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

public class ItemTypeServlet extends HttpServlet{

	/**
	 * 
	 */
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
		String s_itemTypeName = request.getParameter("s_itemTypeName");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		ItemType itemType = new ItemType();
		if("preSave".equals(action)) {
			itemTypePreSave(request, response);
			return;
		} else if("save".equals(action)){
			itemTypeSave(request, response);
			return;
		} else if("delete".equals(action)){
			itemTypeDelete(request, response);
			return;
		} else if("manager".equals(action)){
			itemTypeManager(request, response);
			return;
		} else if("addManager".equals(action)){
			itemTypeAddManager(request, response);
		} else if("move".equals(action)){
			managerMove(request, response);
		} else if("list".equals(action)) {
			if(StringUtil.isNotEmpty(s_itemTypeName)) {
				itemType.setItemTypeName(s_itemTypeName);
			}
			session.removeAttribute("s_itemTypeName");
			request.setAttribute("s_itemTypeName", s_itemTypeName);
		} else if("search".equals(action)){
			if(StringUtil.isNotEmpty(s_itemTypeName)) {
				itemType.setItemTypeName(s_itemTypeName);
				session.setAttribute("s_itemTypeName", s_itemTypeName);
			}else {
				session.removeAttribute("s_itemTypeName");
			}
		} else {
			if(StringUtil.isNotEmpty(s_itemTypeName)) {
				itemType.setItemTypeName(s_itemTypeName);
				session.setAttribute("s_itemTypeName", s_itemTypeName);
			}
			if(StringUtil.isEmpty(s_itemTypeName)) {
				Object o = session.getAttribute("s_itemTypeName");
				if(o!=null) {
					itemType.setItemTypeName((String)o);
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
	
	private void managerMove(HttpServletRequest request,
			HttpServletResponse response) {
		String itemTypeId = request.getParameter("itemTypeId");
		String itemManagerId = request.getParameter("itemManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			itemTypeDao.managerUpdateWithId(con, itemManagerId, "0");
			request.getRequestDispatcher("itemType?action=manager&itemTypeId="+itemTypeId).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void itemTypeAddManager(HttpServletRequest request,
			HttpServletResponse response) {
		String itemTypeId = request.getParameter("itemTypeId");
		String itemManagerId = request.getParameter("itemManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			itemTypeDao.managerUpdateWithId(con, itemManagerId, itemTypeId);
			request.getRequestDispatcher("itemType?action=manager&itemTypeId="+itemTypeId).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void itemTypeManager(HttpServletRequest request,
			HttpServletResponse response) {
		String itemTypeId = request.getParameter("itemTypeId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			List<ItemManager> managerListWithId = itemTypeDao.itemManWithBuildId(con, itemTypeId);
			List<ItemManager> managerListToSelect = itemTypeDao.itemManWithoutBuild(con);
			request.setAttribute("itemTypeId", itemTypeId);
			request.setAttribute("managerListWithId", managerListWithId);
			request.setAttribute("managerListToSelect", managerListToSelect);
			request.setAttribute("mainPage", "admin/selectManager.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void itemTypeDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String itemTypeId = request.getParameter("itemTypeId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if(itemTypeDao.existManOrDeveloperWithId(con, itemTypeId)) {
				request.setAttribute("error", "该项目类别下有管理员或子项目，不能删除该项目类别");
			} else {
				itemTypeDao.itemTypeDelete(con, itemTypeId);
			}
			request.getRequestDispatcher("itemType?action=list").forward(request, response);
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

	private void itemTypeSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String itemTypeId = request.getParameter("itemTypeId");
		String itemTypeName = request.getParameter("itemTypeName");
		String detail = request.getParameter("detail");
		ItemType itemType = new ItemType(itemTypeName, detail);
		if(StringUtil.isNotEmpty(itemTypeId)) {
			itemType.setItemTypeId(Integer.parseInt(itemTypeId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if(StringUtil.isNotEmpty(itemTypeId)) {
				saveNum = itemTypeDao.itemTypeUpdate(con, itemType);
			} else {
				saveNum = itemTypeDao.itemTypeAdd(con, itemType);
			}
			if(saveNum > 0) {
				request.getRequestDispatcher("itemType?action=list").forward(request, response);
			} else {
				request.setAttribute("itemType", itemType);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "itemType/itemTypeSave.jsp");
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

	private void itemTypePreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String itemTypeId = request.getParameter("itemTypeId");
		if(StringUtil.isNotEmpty(itemTypeId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				ItemType itemType = itemTypeDao.itemTypeShow(con, itemTypeId);
				request.setAttribute("itemType", itemType);
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
		request.setAttribute("mainPage", "admin/itemTypeSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
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
