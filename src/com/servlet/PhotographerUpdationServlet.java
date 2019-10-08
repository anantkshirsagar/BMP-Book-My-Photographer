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

import org.apache.commons.fileupload.FileUploadException;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.file.upload.util.FileUploadUtils;
import com.model.FileContent;

@MultipartConfig
public class PhotographerUpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
		try {
			List<FileContent> fileContents = FileUploadUtils.getMultipleFileContents(request);
			for (FileContent fileContent : fileContents) {
				if (fileContent.getFileName() == null)
					continue;
				connectionSettings.build();
				String query = "insert into photo(photo_name,photo,photographer_id) values (?,?,?)";
				PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
				prepareStatement.setString(1, fileContent.getFileName());
				prepareStatement.setBytes(2, fileContent.getBytes());
				prepareStatement.setLong(3, 1);
				prepareStatement.executeUpdate();
				connectionSettings.closeConnection();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
