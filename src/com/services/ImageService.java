package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ImageService extends AbstractDBService {

	public void fetchImages(String photoId, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException {
		connectionSettings.build();
		String query = "select * from photo where id=" + photoId;
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		byte[] bytes = null;
		if (resultSet.next()) {
			bytes = resultSet.getBytes("photo");
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] encode = Base64.getEncoder().encode(bytes);
			outputStream.write(encode, 0, encode.length);
		}
		connectionSettings.closeConnection();

	}
}
