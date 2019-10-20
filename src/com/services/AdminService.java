package com.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.model.FileContent;
import com.tables.Customer;
import com.tables.Order;
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

	public Order getOrderById(String id) throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from order_request where id='" + id + "'";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		Order order = null;
		if (resultSet.next()) {
			order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setTitle(resultSet.getString("title"));
			order.setDate(resultSet.getDate("order_date"));
			order.setTime(resultSet.getTime("order_time"));
			order.setAddress(resultSet.getString("address"));
			order.setNote(resultSet.getString("note"));
			order.setCustomerId(resultSet.getLong("customer_id"));
			order.setPhotographerId(resultSet.getLong("photographer_id"));
		}
		connectionSettings.closeConnection();
		return order;
	}

	public List<FileContent> getPhotosByPhotographerId(String id)
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

	public List<Order> getOrdersByCustomerId(String id) throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from order_request where customer_id=" + id;
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<Order> list = new ArrayList<>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setTitle(resultSet.getString("title"));
			order.setDate(resultSet.getDate("order_date"));
			order.setStatus(resultSet.getString("status"));
			list.add(order);
		}
		connectionSettings.closeConnection();
		LOG.debug("Order List size -> {}", list.size());
		return list;
	}

	public List<Order> getOrdersByPhotographerId(String id) throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from order_request where photographer_id=" + id;
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<Order> list = new ArrayList<>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setTitle(resultSet.getString("title"));
			order.setDate(resultSet.getDate("order_date"));
			order.setStatus(resultSet.getString("status"));
			list.add(order);
		}
		connectionSettings.closeConnection();
		LOG.debug("Order List size -> {}", list.size());
		return list;
	}

	public List<Order> getApprovedOrdersByPhotographerId(String id)
			throws IOException, ClassNotFoundException, SQLException {
		connectionSettings.build();
		String query = "select * from order_request where photographer_id=? and status=?";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, id);
		prepareStatement.setString(2, "APPROVED");
		ResultSet resultSet = prepareStatement.executeQuery();
		List<Order> list = new ArrayList<>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setTitle(resultSet.getString("title"));
			order.setDate(resultSet.getDate("order_date"));
			order.setStatus(resultSet.getString("status"));
			list.add(order);
		}
		connectionSettings.closeConnection();
		LOG.debug("Order List size -> {}", list.size());
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

	public void updateOrder(Order order) throws SQLException, ClassNotFoundException {
		connectionSettings.build();
		String update = "update order_request set title=?,order_date=?,order_time=?,address=?,note=?,customer_id=?,photographer_id=?,status=? where id =?";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(update);
		prepareStatement.setString(1, order.getTitle());
		prepareStatement.setDate(2, order.getDate());
		prepareStatement.setTime(3, order.getTime());
		prepareStatement.setString(4, order.getAddress());
		prepareStatement.setString(5, order.getNote());
		prepareStatement.setLong(6, order.getCustomerId());
		prepareStatement.setLong(7, order.getPhotographerId());
		prepareStatement.setString(8, order.getStatus());
		prepareStatement.setLong(9, order.getId());
		prepareStatement.executeUpdate();
		connectionSettings.closeConnection();
	}

	public List<Photographer> searchPhotographerByFilter(String city, String category)
			throws ClassNotFoundException, SQLException {
		List<Photographer> photographerList = new ArrayList<>();
		if (StringUtils.isNotBlank(city) || StringUtils.isNotBlank(category)) {
			if (StringUtils.isNotBlank(city)) {
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
			if (StringUtils.isNotBlank(category)) {
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

	public boolean isAnyOrderFind(LocalDate date) throws SQLException, ClassNotFoundException {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date.format(df);
		connectionSettings.build();
		String query = "select * from order_request where order_date=?";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setDate(1, java.sql.Date.valueOf(date));
		ResultSet resultSet = prepareStatement.executeQuery();
		if (!resultSet.next()) {
			return true;
		}
		connectionSettings.closeConnection();
		return false;
	}
}
