package com.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tables.Customer;
import com.tables.Order;
import com.tables.Photographer;

public class RegistrationService extends AbstractDBService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

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

	public void insertOrder(Order order) throws ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "insert into order_request(title,order_date,order_time,address,note,customer_id,photographer_id,status) values (?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, order.getTitle());
		prepareStatement.setDate(2, order.getDate());
		prepareStatement.setTime(3, order.getTime());
		prepareStatement.setString(4, order.getAddress());
		prepareStatement.setString(5, order.getNote());
		prepareStatement.setLong(6, order.getCustomerId());
		prepareStatement.setLong(7, order.getPhotographerId());
		prepareStatement.setString(8, order.getStatus());
		LOG.debug("Query: {}", prepareStatement);
		prepareStatement.executeUpdate();
		connectionSettings.closeConnection();
	}
}
