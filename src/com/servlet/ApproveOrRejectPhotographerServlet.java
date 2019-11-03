package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;
import com.tables.Photographer;

public class ApproveOrRejectPhotographerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String photographerId = request.getParameter("id");
			String status = request.getParameter("status");
			AdminService adminService = new AdminService();
			Photographer photographer = adminService.getPhotographerById(photographerId);
			photographer.setStatus(status);
			new AdminService().updatePhotographer(photographer);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
