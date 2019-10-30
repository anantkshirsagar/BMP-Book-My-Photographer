package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.AppConstants.PhotographerStatus;
import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.file.upload.util.FileUploadUtils;
import com.model.FileContent;
import com.services.AdminService;
import com.services.LoginService;
import com.tables.Photographer;

@MultipartConfig
public class PhotographerUpdationServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(PhotographerUpdationServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<FileContent> fileContents = null;
			fileContents = FileUploadUtils.getMultipleFileContents(request);
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			String category = request.getParameter("category");
			AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();

			Photographer photographer = new LoginService().getPhotographerByEmailId(email);
			for (FileContent fileContent : fileContents) {
				if (fileContent.getFileName() == null)
					continue;
				connectionSettings.build();
				String query = "insert into photo(photo_name,photo,photographer_id) values (?,?,?)";
				PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
				prepareStatement.setString(1, fileContent.getFileName());
				prepareStatement.setBytes(2, fileContent.getBytes());
				prepareStatement.setLong(3, photographer.getId());
				prepareStatement.executeUpdate();
				connectionSettings.closeConnection();
			}
			photographer.setCategory(category);
			photographer.setStatus(PhotographerStatus.SUBMITTED.name());
			new AdminService().updatePhotographer(photographer);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<center><h2 style=color:red> Application Submitted for verification !!</h2></center>");
			out.println("<center>Your application will be verified within 3 to 4 working days.</center>");
		} catch (FileUploadException e) {
			LOG.error(e.toString());
		} catch (SQLException e) {
			LOG.error(e.toString());
		} catch (ClassNotFoundException e) {
			LOG.error(e.toString());
		}
	}
}
