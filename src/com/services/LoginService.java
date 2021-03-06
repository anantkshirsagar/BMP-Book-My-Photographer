package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.AppConstants.PhotographerStatus;
import com.model.FileContent;
import com.tables.Customer;
import com.tables.Photographer;

public class LoginService extends AbstractDBService {
	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

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
			photographer.setPassword(resultSet.getString("password"));
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

	public Customer getCustomerByEmailId(String email) throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from customer where email='" + email + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		Customer customer = null;
		if (resultSet.next()) {
			customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setEmail(resultSet.getString("email"));
			customer.setMobileNo(resultSet.getLong("mobile_No"));
		}
		connectionSettings.closeConnection();
		return customer;
	}

	public void addPhoto(List<FileContent> multipartContents, Photographer photographer, String category, String city)
			throws ClassNotFoundException, SQLException {
		for (FileContent fileContent : multipartContents) {
			if (fileContent.getFileName() == null) {
				continue;
			}
			connectionSettings.build();
			String query = "insert into photo(photo_name,photo,photographer_id) values (?,?,?)";
			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
			prepareStatement.setString(1, fileContent.getFileName());
			prepareStatement.setBytes(2, fileContent.getBytes());
			prepareStatement.setLong(3, photographer.getId());
			prepareStatement.executeUpdate();
			connectionSettings.closeConnection();
		}
		photographer.setCategory(category.toLowerCase());
		photographer.setCity(city.toLowerCase());
		photographer.setStatus(PhotographerStatus.SUBMITTED.name());
		new AdminService().updatePhotographer(photographer);
	}
}
