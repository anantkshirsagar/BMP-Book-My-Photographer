package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;
import com.tables.Feedback;
import com.tables.Order;

public class CustomerFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String orderId = request.getParameter("orderId");
			String feedback = request.getParameter("feedback");
			System.out.println(orderId + "  " + feedback);
			AdminService adminService = new AdminService();
			Order order = adminService.getOrderById(orderId);

			Feedback feedbackObj = new Feedback();
			feedbackObj.setFeedback(feedback);
			feedbackObj.setPhotographerId(order.getPhotographerId());
			feedbackObj.setCustomerId(order.getCustomerId());
			long feedbackId = adminService.insertFeedback(feedbackObj);
			order.setFeedbackId(feedbackId);
			adminService.updateOrder(order);
			response.sendRedirect(request.getContextPath() + "/customer-booking-history.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
