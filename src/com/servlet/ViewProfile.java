package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;
import com.tables.Photographer;

public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photographerId = request.getParameter("id");
		AdminService adminService = new AdminService();
		try {
			Photographer photographerById = adminService.getPhotographerById(photographerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
