package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmp.utils.Constants;
import com.services.LoginService;
import com.tables.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String loginType = request.getParameter("type");
		String forwordPageURL = "";
		LoginService loginService = new LoginService();
		User user = null;
		try {
			if (Constants.PHOTOGRAPHER.equals(loginType)) {
				user = loginService.getUserById(Constants.PHOTOGRAPHER, email);
				forwordPageURL = "photographer-home.jsp";
			} else if (Constants.CUSTOMER.equals(loginType)) {
				user = loginService.getUserById(Constants.CUSTOMER, email);
				forwordPageURL = "customer-home.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null && user.getEmail() != null && user.getPassword() != null && email.equals(user.getEmail())
				&& password.equals(user.getPassword())) {
			response.sendRedirect(request.getContextPath() + "/" + forwordPageURL);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<center><h2 style=color:red> Invalid Username or Password !!</h2></center>");
			out.println("<center><a href=login.html>Click here to go to Login page.</a></center>");
		}
	}
}
