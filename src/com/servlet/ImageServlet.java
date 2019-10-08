package com.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imageName = request.getPathInfo().substring(1); // Returns "foo.png".
		try {
			AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
			String query = "select * from photo where photo_name='" + imageName + "'";
			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			System.out.println("Before Image found " + imageName);
			if (resultSet.next()) {
				byte[] content = resultSet.getBytes("photo");
				System.out.println("Image found " + imageName);
				response.setContentType(getServletContext().getMimeType(imageName));
				response.setContentLength(content.length);
				response.getOutputStream().write(content);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {

		}
	}
}
