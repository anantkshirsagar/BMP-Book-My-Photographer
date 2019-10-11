package com.servlet;

import java.io.IOException;
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

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.file.upload.util.FileUploadUtils;
import com.model.FileContent;
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
			String PATH = "S:\\suyog\\NSG 2019\\BMP-Book-My-Photographer\\WebContent\\images";
			AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();

			Photographer photographer = new LoginService().getPhotographerByEmailId(email);
			for (FileContent fileContent : fileContents) {
				if (fileContent.getFileName() == null)
					continue;
				FileUploadUtils.writeStream(fileContent.getInputStream(), fileContent.getFileName(), PATH);
				connectionSettings.build();
				String query = "insert into photo(photo_name,photo,photographer_id) values (?,?,?)";
				PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
				prepareStatement.setString(1, fileContent.getFileName());
				prepareStatement.setBytes(2, fileContent.getBytes());
				prepareStatement.setLong(3, photographer.getId());
				prepareStatement.executeUpdate();
				connectionSettings.closeConnection();
			}
			connectionSettings.build();
			String update = "update photographer set name=?,email=?,password=?,mobile_no=?,is_approved=?,is_submitted=?,category=? where email =?";
			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(update);
			prepareStatement.setString(1, photographer.getName());
			prepareStatement.setString(2, photographer.getEmail());
			prepareStatement.setString(3, photographer.getPassword());
			prepareStatement.setLong(4, photographer.getMobileNo());
			prepareStatement.setBoolean(5, false);
			prepareStatement.setBoolean(6, true);
			prepareStatement.setString(7, category);
			prepareStatement.setString(8, email);
			prepareStatement.executeUpdate();
			connectionSettings.closeConnection();
		} catch (FileUploadException e) {
			LOG.error(e.toString());
		} catch (SQLException e) {
			LOG.error(e.toString());
		} catch (ClassNotFoundException e) {
			LOG.error(e.toString());
		}
	}
}
