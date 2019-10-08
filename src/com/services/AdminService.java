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

	public List<Photographer> getPhotographerApprovalList() throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photographer where is_submitted=true and is_approved=false";
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
		LOG.debug("Approval List size -> {}", list.size());
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

	public List<FileContent> getPhotosByIdPhotographer(long id)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from photo where photographer_id=" + id;
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<FileContent> list = new ArrayList<>();
		while (resultSet.next()) {
			FileContent fileContent = new FileContent();
			fileContent.setFileName(resultSet.getString("photo_name"));
			fileContent.setBytes(resultSet.getBytes("photo"));
			list.add(fileContent);
		}
		connectionSettings.closeConnection();
		LOG.debug("Photo List size -> {}", list.size());
		return list;
	}
}
