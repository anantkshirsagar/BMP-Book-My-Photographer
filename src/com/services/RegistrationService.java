package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.tables.Customer;
import com.tables.Photographer;

public class RegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);
	private AbstractConnectionSettings connectionSettings;

	public RegistrationService() throws IOException {
		connectionSettings = ConnectionUtils.getConnectionSettings();
	}

	public void insertPhotographer(Photographer photographer) throws ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "insert into photographer(name,email,password,mobile_no,status) values (?,?,?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, photographer.getName());
		prepareStatement.setString(2, photographer.getEmail());
		prepareStatement.setString(3, photographer.getPassword());
		prepareStatement.setLong(4, photographer.getMobileNo());
		prepareStatement.setString(5, photographer.getStatus());
		prepareStatement.executeUpdate();
		LOG.debug("Photographer inserted");
		connectionSettings.closeConnection();
	}

	public void insertCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "insert into customer(name,email,password,mobile_no) values (?,?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, customer.getName());
		prepareStatement.setString(2, customer.getEmail());
		prepareStatement.setString(3, customer.getPassword());
		prepareStatement.setLong(4, customer.getMobileNo());
		prepareStatement.executeUpdate();
		LOG.debug("Customer inserted");
		connectionSettings.closeConnection();
	}
}
