package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bmp.utils.ConnetionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.tables.User;

public class RegistrationService {
	public void insertUser(String tableName, User user) throws ClassNotFoundException, SQLException, IOException {
		AbstractConnectionSettings connectionSettings = ConnetionUtils.getConnectionSettings();
		connectionSettings.build();
		String query = "insert into " + tableName.toLowerCase() + "(name,email,password,mobile_no) values (?,?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, user.getName());
		prepareStatement.setString(2, user.getEmail());
		prepareStatement.setString(3, user.getPassword());
		prepareStatement.setLong(4, user.getMobileNo());
		prepareStatement.executeUpdate();
		connectionSettings.closeConnection();
	}
}
