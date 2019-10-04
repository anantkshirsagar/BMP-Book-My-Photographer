package com.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;

@MultipartConfig
public class PhotographerUpdationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			System.out.println(part);
//			connectionSettings.build();
//			String query = "insert into photo(photo,photographer_id) values (?,?)";
//			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
//			prepareStatement.setBlob(1, part);
//			prepareStatement.setString(2, photographer.getEmail());
//			prepareStatement.setString(3, photographer.getPassword());
//			prepareStatement.setLong(4, photographer.getMobileNo());
//			prepareStatement.setBoolean(5, false);
//			prepareStatement.setBoolean(6, false);
//			prepareStatement.executeUpdate();
//			LOG.debug("Photographer inserted");
//			connectionSettings.closeConnection();

		}
	}
}
