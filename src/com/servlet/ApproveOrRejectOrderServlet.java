package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;
import com.tables.Order;

public class ApproveOrRejectOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String orderId = (String) request.getParameter("id");
			String status = (String) request.getParameter("status");
			AdminService adminService = new AdminService();
			Order order = adminService.getOrderById(orderId);
			order.setStatus(status);
			new AdminService().updateOrderWithoutFeedback(order);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
