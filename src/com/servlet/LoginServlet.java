package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.AppConstants;
import com.bmp.utils.AppConstants.UserType;
import com.services.LoginService;
import com.tables.Photographer;

public class LoginServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String loginType = request.getParameter("type");
		String forwordPageURL = null;
		LoginService loginService = new LoginService();
		HttpSession session = request.getSession();
		// System.out.println("email " + email + " password " + password);
		boolean loginAllowFlag = false;
		try {
			if (UserType.PHOTOGRAPHER.name().equals(loginType)) {
				loginAllowFlag = loginService.isPhotographerLoginAllow(email, password);
				if (loginAllowFlag) {
					Photographer photographer = loginService.getPhotographerByEmailId(email);
					if (photographer.isApproved()) {
						forwordPageURL = "photographer-home.jsp";
					} else if (photographer.isSubmitted()) {
						out.println(
								"<center><h2 style=color:red> Your Application is not verified yet !!</h2></center>");
						out.println("<center>Please try again after some time.</center>");
					} else {
						forwordPageURL = "temp-photorapher-home.jsp";
					}
				}
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
		if (loginAllowFlag && forwordPageURL != null) {
			session.setAttribute("email", email);
			response.sendRedirect(request.getContextPath() + "/" + forwordPageURL);
		} else {
			out.println("<center><h2 style=color:red> Invalid Username or Password !!</h2></center>");
			out.println("<center><a href=login.html>Click here to go to Login page.</a></center>");
		}
	}
}
