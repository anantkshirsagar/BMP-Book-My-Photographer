package com.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.services.AdminService;
import com.tables.Photographer;

public class CustomerSearchPhotographer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String city = request.getParameter("city");
			String category = request.getParameter("category");
			AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
			List<Photographer> photographerList = new AdminService().searchPhotographerByFilter(city, category);
			HttpSession session = request.getSession();
			session.setAttribute("photographerList", photographerList);
			session.setAttribute("category", category);
			session.setAttribute("city", city);
			connectionSettings.closeConnection();
			response.sendRedirect(request.getContextPath() + "/customer-search-photographer.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
