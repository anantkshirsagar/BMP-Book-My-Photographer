package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.ServletUtils;
import com.file.upload.util.FileUploadUtils;
import com.model.FileContent;
import com.services.LoginService;
import com.tables.FormFields;
import com.tables.Photographer;

@MultipartConfig
public class PhotographerUpdationServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(PhotographerUpdationServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<FileItem> fileItems = FileUploadUtils.getFileItems(request);
			List<FormFields> formFieldContents = ServletUtils.getFormFieldContents(fileItems);
			String category = ServletUtils.getParameter(formFieldContents, "category");
			String city = ServletUtils.getParameter(formFieldContents, "city");
			List<FileContent> multipartContents = ServletUtils.getMultipartContents(fileItems);

			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			LoginService loginService = new LoginService();
			Photographer photographer = loginService.getPhotographerByEmailId(email);
			loginService.addPhoto(multipartContents, photographer, category, city);

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<center><h2 style=color:red> Application Submitted for verification !!</h2></center>");
			out.println("<center>Your application will be verified within 3 to 4 working days.</center>");
			out.println("<br><center><a href=login.html>Click here to go to Login page.</a></center>");
			session.removeAttribute("email");
			session.invalidate();
		} catch (FileUploadException e) {
			LOG.error(e.toString());
		} catch (SQLException e) {
			LOG.error(e.toString());
		} catch (ClassNotFoundException e) {
			LOG.error(e.toString());
		}
	}
}
