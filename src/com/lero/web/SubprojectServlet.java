package com.lero.web;

import com.lero.dao.DeveloperDao;
import com.lero.dao.ItemTypeDao;
import com.lero.dao.SubprojectDao;
import com.lero.model.Developer;
import com.lero.model.ItemManager;
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
 * @Description : 任务控制
 * @Author : 陈宏兴
 * @data : 2019/3/28
 */
public class SubprojectServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	SubprojectDao subprojectDao = new SubprojectDao();
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
		Object currentUserType = session.getAttribute("currentUserType");
		String s_subprojectText = request.getParameter("s_subprojectText");
		String itemTypeId = request.getParameter("buildToSelect");
		String searchType = request.getParameter("searchType");
		String action = request.getParameter("action");
		Subproject subproject = new Subproject();
		
		if("preSave".equals(action)) {
			//编辑
			subprojectPreSave(request, response);
			return;
		} else if("save".equals(action)){
			//保存
			subprojectSave(request, response);
			return;
		} else if("delete".equals(action)){
			//删除
			subprojectDelete(request, response);
			return;
		} else if("list".equals(action)) {
			//列表
			if(StringUtil.isNotEmpty(s_subprojectText)) {
				//搜索
				if("name".equals(searchType)) {
					subproject.setName(s_subprojectText);
				} else if("number".equals(searchType)) {
					subproject.setSubNumber(s_subprojectText);
				} else if("developer".equals(searchType)) {
					subproject.setDeveloperName(s_subprojectText);
				}
			}
			if(StringUtil.isNotEmpty(itemTypeId)) {
				subproject.setItemTypeId(Integer.parseInt(itemTypeId));
			}
			session.removeAttribute("s_subprojectText");
			session.removeAttribute("searchType");
			session.removeAttribute("buildToSelect");
			request.setAttribute("s_subprojectText", s_subprojectText);
			request.setAttribute("searchType", searchType);
			request.setAttribute("buildToSelect", itemTypeId);
		} else if("search".equals(action)){

			if(StringUtil.isNotEmpty(s_subprojectText)) {
				if("name".equals(searchType)) {
					subproject.setName(s_subprojectText);
				} else if("number".equals(searchType)) {
					subproject.setSubNumber(s_subprojectText);
				} else if("developer".equals(searchType)) {
					subproject.setDeveloperName(s_subprojectText);
				}
				session.setAttribute("s_subprojectText", s_subprojectText);
				session.setAttribute("searchType", searchType);
			} else {
				session.removeAttribute("s_subprojectText");
				session.removeAttribute("searchtype");
			}
			if(StringUtil.isNotEmpty(itemTypeId)) {
				subproject.setItemTypeId(Integer.parseInt(itemTypeId));
				session.setAttribute("buildToSelect", itemTypeId);
			}else {
				session.removeAttribute("buildToSelect");
			}
		} else {

			if("admin".equals((String)currentUserType)) {
				if(StringUtil.isNotEmpty(s_subprojectText)) {
					if("name".equals(searchType)) {
						subproject.setName(s_subprojectText);
					} else if("number".equals(searchType)) {
						subproject.setSubNumber(s_subprojectText);
					} else if("developer".equals(searchType)) {
						subproject.setDeveloperName(s_subprojectText);
					}
					session.setAttribute("s_subprojectText", s_subprojectText);
					session.setAttribute("searchType", searchType);
				}
				if(StringUtil.isNotEmpty(itemTypeId)) {
					subproject.setItemTypeId(Integer.parseInt(itemTypeId));
					session.setAttribute("buildToSelect", itemTypeId);
				}
				if(StringUtil.isEmpty(s_subprojectText) && StringUtil.isEmpty(itemTypeId)) {
					Object o1 = session.getAttribute("s_subprojectText");
					Object o2 = session.getAttribute("searchType");
					Object o3 = session.getAttribute("buildToSelect");
					if(o1!=null) {
						if("name".equals((String)o2)) {
							subproject.setName((String)o1);
						} else if("number".equals((String)o2)) {
							subproject.setSubNumber((String)o1);
						} else if("developer".equals((String)o2)) {
							subproject.setDeveloperName((String)o1);
						}
					}
					if(o3 != null) {
						subproject.setItemTypeId(Integer.parseInt((String)o3));
					}
				}
			} else if("itemManager".equals((String)currentUserType)) {

				if(StringUtil.isNotEmpty(s_subprojectText)) {
					if("name".equals(searchType)) {
						subproject.setName(s_subprojectText);
					} else if("number".equals(searchType)) {
						subproject.setSubNumber(s_subprojectText);
					} else if("developer".equals(searchType)) {
						subproject.setDeveloperName(s_subprojectText);
					}
					session.setAttribute("s_subprojectText", s_subprojectText);
					session.setAttribute("searchType", searchType);
				}
				if(StringUtil.isEmpty(s_subprojectText)) {
					Object o1 = session.getAttribute("s_subprojectText");
					Object o2 = session.getAttribute("searchType");
					if(o1!=null) {
						if("name".equals((String)o2)) {
							subproject.setName((String)o1);
						} else if("number".equals((String)o2)) {
							subproject.setSubNumber((String)o1);
						} else if("developer".equals((String)o2)) {
							subproject.setDeveloperName((String)o1);
						}
					}
				}
			}
		}
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if("admin".equals((String)currentUserType)) {
				List<Subproject> subprojectList = subprojectDao.subprojectList(con, subproject);
				request.setAttribute("itemTypeList", subprojectDao.itemTypeList(con));
				request.setAttribute("subprojectList", subprojectList);
				request.setAttribute("mainPage", "admin/subproject.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);

			} else if("itemManager".equals((String)currentUserType)) {
				ItemManager manager = (ItemManager)(session.getAttribute("currentUser"));
				int buildId = manager.getItemTypeId();
				String buildName = ItemTypeDao.itemTypeName(con, buildId);
				List<Subproject> subprojectList = subprojectDao.subprojectListWithBuild(con, subproject, buildId);
				request.setAttribute("itemTypeName", buildName);
				request.setAttribute("subprojectList", subprojectList);
				request.setAttribute("mainPage", "itemManager/subproject.jsp");
				request.getRequestDispatcher("mainManager.jsp").forward(request, response);

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

	/**
	 * 删除任务
	 * @param request
	 * @param response
	 */
	private void subprojectDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String subprojectId = request.getParameter("subprojectId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			subprojectDao.subprojectDelete(con, subprojectId);
			request.setAttribute("error", "删除成功");
			request.getRequestDispatcher("subproject?action=list").forward(request, response);
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

	/**
	 * 保存任务
	 * @param request
	 * @param response
	 */
	private void subprojectSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		long start = System.currentTimeMillis();//测试开始时间

		String subprojectId = request.getParameter("subprojectId");
		String subNumber = request.getParameter("subNumber");
		String itemTypeId = request.getParameter("itemTypeId");
		String developerId = request.getParameter("developerId");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String tel = request.getParameter("tel");

		Subproject subproject = new Subproject(subNumber, Integer.parseInt(itemTypeId), developerId, name, state);
		if(StringUtil.isNotEmpty(subprojectId)) {
			subproject.setSubprojectId(Integer.parseInt(subprojectId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Developer developer = developerDao.developerShow(con, developerId);
			if(developer!=null){
				subproject.setDeveloperName(developer.getName());
				subproject.setTel(developer.getTel());
			}
			int saveNum = 0;
			if(StringUtil.isNotEmpty(subprojectId)) {
				saveNum = subprojectDao.subprojectUpdate(con, subproject);
			} else if(subprojectDao.haveNameByNumber(con, subproject.getSubNumber())){
				request.setAttribute("subproject", subproject);
				request.setAttribute("error", "该编号已存在");
				request.setAttribute("mainPage", "admin/subprojectSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = subprojectDao.subprojectAdd(con, subproject);
			}
			if(saveNum > 0) {
				request.setAttribute("error", "保存成功");
				request.getRequestDispatcher("subproject?action=list").forward(request, response);
			} else {
				request.setAttribute("subproject", subproject);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "admin/subprojectSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long end = System.currentTimeMillis();//测试结束时间
			System.out.println(end-start+"毫秒");//测试运行时间
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 进入任务编辑
	 * @param request
	 * @param response
	 */
	private void subprojectPreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String subprojectId = request.getParameter("subprojectId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			request.setAttribute("developerList", developerDao.developerList(con,null,null));
			request.setAttribute("itemTypeList", subprojectDao.itemTypeList(con));
			if (StringUtil.isNotEmpty(subprojectId)) {
				Subproject subproject = subprojectDao.subprojectShow(con, subprojectId);
				request.setAttribute("subproject", subproject);
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
		request.setAttribute("mainPage", "admin/subprojectSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}
	
}
