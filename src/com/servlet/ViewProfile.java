package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.AdminService;
import com.tables.Photographer;

public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String photographerId = request.getParameter("id");
			AdminService adminService = new AdminService();
			Photographer photographer = adminService.getPhotographerById(photographerId);
			HttpSession session = request.getSession();
			session.setAttribute("photographer", photographer);
			response.sendRedirect(request.getContextPath() + "/view-profile.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
