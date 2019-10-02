package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.AppConstants;
import com.bmp.utils.AppConstants.UserType;
import com.services.LoginService;

public class LoginServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String loginType = request.getParameter("type");
		String forwordPageURL = "";
		LoginService loginService = new LoginService();
		// System.out.println("email " + email + " password " + password);
		boolean loginAllowFlag = false;
		try {
			if (UserType.PHOTOGRAPHER.name().equals(loginType)) {
				loginAllowFlag = loginService.isPhotographerLoginAllow(email, password);
				forwordPageURL = "photographer-home.jsp";
			} else if (UserType.CUSTOMER.name().equals(loginType)) {
				loginAllowFlag = loginService.isCustomerLoginAllow(email, password);
				forwordPageURL = "customer-home.jsp";
			} else if (UserType.ADMIN.name().equals(loginType)) {
				if (AppConstants.ADMIN_USERNAME.equals(email) && AppConstants.ADMIN_PASSWORD.equals(password)) {
					forwordPageURL = "admin-home.jsp";
					loginAllowFlag = true;
				}
			}
		} catch (Exception e) {
			LOG.error("Error {}", e);
			e.printStackTrace();
		}
		if (loginAllowFlag) {
			response.sendRedirect(request.getContextPath() + "/" + forwordPageURL);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<center><h2 style=color:red> Invalid Username or Password !!</h2></center>");
			out.println("<center><a href=login.html>Click here to go to Login page.</a></center>");
		}
	}
}
