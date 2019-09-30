package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bmp.utils.ConnetionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.tables.User;

public class LoginService {

	public User getUserById(String tableName, String email) throws IOException, ClassNotFoundException, SQLException {
		AbstractConnectionSettings connectionSettings = ConnetionUtils.getConnectionSettings();
		connectionSettings.build();
		String query = "select * from " + tableName.toLowerCase() + " where email='" + email + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		User user = new User();
		if (resultSet.next()) {
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
		}
		connectionSettings.closeConnection();
		return user;
	}
}
