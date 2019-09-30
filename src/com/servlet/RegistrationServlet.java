package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.Constants.UserType;
import com.services.RegistrationService;
import com.tables.User;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
		String registrationType = request.getParameter("type");
		RegistrationService registrationService = new RegistrationService();
		try {
			if (UserType.PHOTOGRAPHER.toString().equals(registrationType)) {
				LOG.debug("Creating Photographer {}", user);
				registrationService.insertUser(UserType.PHOTOGRAPHER.toString(), user);
			} else if (UserType.CUSTOMER.toString().equals(registrationType)) {
				LOG.debug("Creating Customer {}", user);
				registrationService.insertUser(UserType.CUSTOMER.toString(), user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/login.html");
	}
}
