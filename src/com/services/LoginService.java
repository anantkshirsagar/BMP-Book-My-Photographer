package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.tables.Photographer;

public class LoginService {
	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
	private AbstractConnectionSettings connectionSettings;

	public LoginService() throws IOException {
		connectionSettings = ConnectionUtils.getConnectionSettings();
	}

	public boolean isCustomerLoginAllow(String email, String password)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		boolean loginFlag = false;
		String query = "select * from customer where email='" + email + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			if (email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password"))) {
				loginFlag = true;
			}
		}
		LOG.debug("Email :" + email + " Login Allow : " + loginFlag);
		connectionSettings.closeConnection();
		return loginFlag;
	}

	public boolean isPhotographerLoginAllow(String email, String password)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		boolean loginFlag = false;
		String query = "select * from photographer where email='" + email + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			if (email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password"))) {
				loginFlag = true;
			}
		}
		connectionSettings.closeConnection();
		LOG.debug("Email :" + email + " Login Allow : " + loginFlag);
		return loginFlag;
	}

	public Photographer getPhotographerByEmailId(String email)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photographer where email='" + email + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		Photographer photographer = null;
		if (resultSet.next()) {
			photographer = new Photographer();
			photographer.setId(resultSet.getLong("id"));
			photographer.setName(resultSet.getString("name"));
			photographer.setEmail(resultSet.getString("email"));
			photographer.setMobileNo(resultSet.getLong("mobile_No"));
			photographer.setCity(resultSet.getString("city"));
			photographer.setCameraType(resultSet.getString("camera_type"));
			photographer.setCategory(resultSet.getString("category"));
			photographer.setStatus(resultSet.getString("status"));
		}
		connectionSettings.closeConnection();
		LOG.debug("Email :" + email + " Approved : " + photographer.getStatus());
		return photographer;
	}
}
