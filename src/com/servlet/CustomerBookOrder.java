package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.LoginService;
import com.services.RegistrationService;
import com.tables.Customer;
import com.tables.Order;

public class CustomerBookOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			long photographerId = (Long) session.getAttribute("photographerId");
			session.removeAttribute("photographerId");
			LoginService loginService = new LoginService();
			Customer customer = loginService.getCustomerByEmailId(email);
			Order order = new Order();
			order.setTitle(request.getParameter("title"));
			// order.setDate(request.getParameter("date"));
			// order.setTime(request.getParameter("time"));
			order.setAddress(request.getParameter("address"));
			order.setNote(request.getParameter("note"));
			order.setPhotographerId(photographerId);
			order.setCustomerId(customer.getId());
			new RegistrationService().insertOrder(order);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
