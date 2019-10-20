package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmp.utils.UtilService;
import com.services.LoginService;
import com.services.RegistrationService;
import com.tables.Customer;
import com.tables.Order;

public class CustomerBookOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			String photographerId = (String) session.getAttribute("photographerId");
			LoginService loginService = new LoginService();
			Customer customer = loginService.getCustomerByEmailId(email);
			String time = request.getParameter("time");
			Order order = new Order();
			order.setTitle(request.getParameter("title"));
			order.setAddress(request.getParameter("address"));
			order.setNote(request.getParameter("note"));
			order.setPhotographerId(Long.valueOf(photographerId));
			order.setCustomerId(customer.getId());
			String date = request.getParameter("date");
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			order.setDate(UtilService.getSQLDate(sdfDate.parse(date)));
			SimpleDateFormat sdfTime = new SimpleDateFormat("kk:mm");
			order.setTime(UtilService.getSQLTime(sdfTime.parse(time)));
			new RegistrationService().insertOrder(order);
			response.sendRedirect(request.getContextPath() + "/customer-booking-history.jsp");
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

}
