package com.servlet.bmp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		PrintWriter out = response.getWriter();
		out.println(jsonObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		// ResponseUtils.setResponseParameter(response);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		User user1 = new User();
		user1.setId(101);
		user1.setName("Pranav Joshi");
		System.out.println("forwarding to jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("test.jsp");
		request.setAttribute("user", user1);
		requestDispatcher.forward(request, response);
		// out.write(jsonObj);
	}
}
