package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.AppConstants.PhotographerStatus;
import com.bmp.utils.AppConstants.UserType;
import com.services.RegistrationService;
import com.tables.Customer;
import com.tables.Photographer;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registrationType = request.getParameter("type");
		LOG.debug("registrationType {}", registrationType);
		RegistrationService registrationService = new RegistrationService();
		try {
			if (UserType.PHOTOGRAPHER.name().equals(registrationType)) {
				Photographer photographer = new Photographer();
				photographer.setName(request.getParameter("name"));
				photographer.setEmail(request.getParameter("email"));
				photographer.setPassword(request.getParameter("password"));
				photographer.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
				photographer.setStatus(PhotographerStatus.NOT_SUBMITTED.name());
				LOG.debug("Creating Photographer {}", photographer);
				registrationService.insertPhotographer(photographer);
			} else if (UserType.CUSTOMER.name().equals(registrationType)) {
				Customer customer = new Customer();
				customer.setName(request.getParameter("name"));
				customer.setEmail(request.getParameter("email"));
				customer.setPassword(request.getParameter("password"));
				customer.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
				LOG.debug("Creating Customer {}", customer);
				registrationService.insertCustomer(customer);
			}
		} catch (Exception e) {
			LOG.error("Exception {}", e);
		}
		response.sendRedirect(request.getContextPath() + "/login.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
