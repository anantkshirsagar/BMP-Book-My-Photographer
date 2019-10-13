package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.model.FileContent;
import com.tables.Photographer;

public class AdminService {
	private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);
	private AbstractConnectionSettings connectionSettings;

	public AdminService() throws IOException {
		connectionSettings = ConnectionUtils.getConnectionSettings();
	}

	public List<Photographer> getPhotographerListByStatus(String status)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photographer where status='" + status + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<Photographer> list = new ArrayList<>();
		while (resultSet.next()) {
			Photographer photographer = new Photographer();
			photographer.setId(resultSet.getLong("id"));
			photographer.setName(resultSet.getString("name"));
			photographer.setEmail(resultSet.getString("email"));
			photographer.setCity(resultSet.getString("city"));
			list.add(photographer);
		}
		LOG.debug("Photographer List size -> {} of status -> {}", list.size(), status);
		connectionSettings.closeConnection();
		return list;
	}

	public Photographer getPhotographerById(String id) throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photographer where id='" + id + "'";
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
		}
		connectionSettings.closeConnection();
		return photographer;
	}

	public List<FileContent> getPhotosByIdPhotographer(String id)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photo where photographer_id=" + id;
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<FileContent> list = new ArrayList<>();
		while (resultSet.next()) {
			FileContent fileContent = new FileContent();
			fileContent.setFileName(resultSet.getInt("id") + "");
			fileContent.setBytes(resultSet.getBytes("photo"));
			list.add(fileContent);
		}
		connectionSettings.closeConnection();
		LOG.debug("Photo List size -> {}", list.size());
		return list;
	}

	public void updatePhotographer(Photographer photographer) throws SQLException, ClassNotFoundException {
		connectionSettings.build();
		String update = "update photographer set name=?,email=?,password=?,mobile_no=?,status=?,category=? where email =?";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(update);
		prepareStatement.setString(1, photographer.getName());
		prepareStatement.setString(2, photographer.getEmail());
		prepareStatement.setString(3, photographer.getPassword());
		prepareStatement.setLong(4, photographer.getMobileNo());
		prepareStatement.setString(5, photographer.getStatus());
		prepareStatement.setString(6, photographer.getCategory());
		prepareStatement.setString(7, photographer.getEmail());
		prepareStatement.executeUpdate();
		connectionSettings.closeConnection();
	}
	
	public List<Photographer> searchPhotographerByFilter(String city, String category)
			throws ClassNotFoundException, SQLException{
		List<Photographer> photographerList = new ArrayList<>();
		if (city != null || category != null) {
			if (city != null) {
				connectionSettings.build();
				String query = "select * from photographer where city='" + city.toLowerCase()
						+ "' and status='APPROVED'";
				PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
				ResultSet resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
					Photographer photographer = new Photographer();
					photographer.setId(resultSet.getLong("id"));
					photographer.setName(resultSet.getString("name"));
					photographer.setEmail(resultSet.getString("email"));
					photographer.setMobileNo(resultSet.getLong("mobile_no"));
					photographerList.add(photographer);
				}
			}
			if (category != null) {
				connectionSettings.build();
				String query = "select * from photographer where category='" + category.toLowerCase()
						+ "' and status='APPROVED'";
				PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
				ResultSet resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
					Photographer photographer = new Photographer();
					photographer.setId(resultSet.getLong("id"));
					photographer.setName(resultSet.getString("name"));
					photographer.setEmail(resultSet.getString("email"));
					photographer.setMobileNo(resultSet.getLong("mobile_no"));
					photographerList.add(photographer);
				}
			}
		} else {
			connectionSettings.build();
			String query = "select * from photographer where status='APPROVED'";
			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Photographer photographer = new Photographer();
				photographer.setId(resultSet.getLong("id"));
				photographer.setName(resultSet.getString("name"));
				photographer.setEmail(resultSet.getString("email"));
				photographer.setMobileNo(resultSet.getLong("mobile_no"));
				photographerList.add(photographer);
			}
		}
		return photographerList;
	}
}
