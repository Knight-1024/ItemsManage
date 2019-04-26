package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.ItemTypeDao;
import com.lero.dao.RecordDao;
import com.lero.dao.SubprojectDao;
import com.lero.model.ItemManager;
import com.lero.model.Record;
import com.lero.model.Subproject;
import com.lero.util.DbUtil;
import com.lero.util.StringUtil;

public class RecordServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	RecordDao recordDao = new RecordDao();
	
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
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		Record record = new Record();
		if("preSave".equals(action)) {
			recordPreSave(request, response);
			return;
		} else if("save".equals(action)){
			recordSave(request, response);
			return;
		} else if("delete".equals(action)){
			recordDelete(request, response);
			return;
		} else if("list".equals(action)) {
			if(StringUtil.isNotEmpty(s_subprojectText)) {
				if("name".equals(searchType)) {
					record.setSubprojectName(s_subprojectText);
				} else if("number".equals(searchType)) {
					record.setSubprojectNumber(s_subprojectText);
				} else if("dorm".equals(searchType)) {
					record.setDeveloperName(s_subprojectText);
				}
			}
			if(StringUtil.isNotEmpty(itemTypeId)) {
				record.setItemTypeId(Integer.parseInt(itemTypeId));
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
					record.setSubprojectName(s_subprojectText);
				} else if("number".equals(searchType)) {
					record.setSubprojectNumber(s_subprojectText);
				} else if("dorm".equals(searchType)) {
					record.setDeveloperName(s_subprojectText);
				}
				session.setAttribute("s_subprojectText", s_subprojectText);
				session.setAttribute("searchType", searchType);
			} else {
				session.removeAttribute("s_subprojectText");
				session.removeAttribute("searchType");
			}
			if(StringUtil.isNotEmpty(startDate)) {
				record.setStartDate(startDate);
				session.setAttribute("startDate", startDate);
			} else {
				session.removeAttribute("startDate");
			}
			if(StringUtil.isNotEmpty(endDate)) {
				record.setEndDate(endDate);
				session.setAttribute("endDate", endDate);
			} else {
				session.removeAttribute("endDate");
			}
			if(StringUtil.isNotEmpty(itemTypeId)) {
				record.setItemTypeId(Integer.parseInt(itemTypeId));
				session.setAttribute("buildToSelect", itemTypeId);
			}else {
				session.removeAttribute("buildToSelect");
			}
		} 
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if("admin".equals((String)currentUserType)) {
				List<Record> recordList = recordDao.recordList(con, record);
				request.setAttribute("itemTypeList", recordDao.itemTypeList(con));
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "admin/record.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
			} else if("itemManager".equals((String)currentUserType)) {
				ItemManager manager = (ItemManager)(session.getAttribute("currentUser"));
				int buildId = manager.getItemTypeId();
				String buildName = ItemTypeDao.itemTypeName(con, buildId);
				List<Record> recordList = recordDao.recordListWithBuild(con, record, buildId);
				request.setAttribute("itemTypeName", buildName);
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "itemManager/record.jsp");
				request.getRequestDispatcher("mainManager.jsp").forward(request, response);
			} else if("subproject".equals((String)currentUserType)) {
				Subproject subproject = (Subproject)(session.getAttribute("currentUser"));
				List<Record> recordList = recordDao.recordListWithNumber(con, record, subproject.getSubNumber());
				request.setAttribute("recordList", recordList);
				request.setAttribute("mainPage", "subproject/record.jsp");
				request.getRequestDispatcher("mainSubproject.jsp").forward(request, response);
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

	private void recordDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String recordId = request.getParameter("recordId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			recordDao.recordDelete(con, recordId);
			request.getRequestDispatcher("record?action=list").forward(request, response);
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

	private void recordSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String recordId = request.getParameter("recordId");
		String subprojectNumber = request.getParameter("subprojectNumber");
		String date = request.getParameter("date");
		String detail = request.getParameter("detail");
		Record record = new Record(subprojectNumber, date, detail);
		if(StringUtil.isNotEmpty(recordId)) {
			if(Integer.parseInt(recordId)!=0) {
				record.setRecordId(Integer.parseInt(recordId));
			}
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			HttpSession session = request.getSession();
			ItemManager manager = (ItemManager)(session.getAttribute("currentUser"));
			int buildId = manager.getItemTypeId();
			Subproject subproject = SubprojectDao.getNameById(con, subprojectNumber, buildId);
			if(subproject.getName() == null) {
				request.setAttribute("record", record);
				request.setAttribute("error", "编号不在您管理的项目范围内");
				request.setAttribute("mainPage", "itemManager/recordSave.jsp");
				request.getRequestDispatcher("mainManager.jsp").forward(request, response);
			} else {
				record.setItemTypeId(subproject.getItemTypeId());
				record.setSubprojectName(subproject.getName());
				record.setDeveloperName(subproject.getDeveloperName());
				if(StringUtil.isNotEmpty(recordId) && Integer.parseInt(recordId)!=0) {
					saveNum = recordDao.recordUpdate(con, record);
				} else {
					saveNum = recordDao.recordAdd(con, record);
				}
				if(saveNum > 0) {
					request.getRequestDispatcher("record?action=list").forward(request, response);
				} else {
					request.setAttribute("record", record);
					request.setAttribute("error", "保存失败");
					request.setAttribute("mainPage", "itemManager/recordSave.jsp");
					request.getRequestDispatcher("mainManager.jsp").forward(request, response);
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

	private void recordPreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String recordId = request.getParameter("recordId");
		String subprojectNumber = request.getParameter("subprojectNumber");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(recordId)) {
				Record record = recordDao.recordShow(con, recordId);
				request.setAttribute("record", record);
			} else {
				Calendar rightNow = Calendar.getInstance();       
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");   
				String sysDatetime = fmt.format(rightNow.getTime());
				request.setAttribute("subprojectNumber", subprojectNumber);
				request.setAttribute("date", sysDatetime);
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
		request.setAttribute("mainPage", "itemManager/recordSave.jsp");
		request.getRequestDispatcher("mainManager.jsp").forward(request, response);
	}
	
}
