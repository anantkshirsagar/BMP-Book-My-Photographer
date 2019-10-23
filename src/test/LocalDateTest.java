package test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bmp.utils.ConnectionUtils;
import com.bmp.utils.UtilService;
import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.mysql.jdbc.Statement;
import com.tables.Feedback;
import com.tables.Order;

public class LocalDateTest {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// AbstractConnectionSettings connectionSettings =
		// ConnectionUtils.getConnectionSettings();
		// String query = "insert into test(id,date) values (?,?)";
		// PreparedStatement prepareStatement =
		// connectionSettings.getConnection().prepareStatement(query);
		// prepareStatement.setInt(1, 1);
		// prepareStatement.setDate(2, null);
		// prepareStatement.executeUpdate();
		// connectionSettings.closeConnection();
		// Date date = new Date();
		// System.out.println(UtilService.getSQLDate(date));
		// System.out.println(UtilService.getSQLTime(date));
		// String name = LocalDate.now().getMonth().name().toLowerCase();
		// String capitalize = StringUtils.capitalize(name);
		// System.out.print(capitalize);
		// LocalDate date = LocalDate.now();
		// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// date.format(df);
		AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
		connectionSettings.build();
		String query = "insert into feedback(feedback,customer_id,photographer_id) values (?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		prepareStatement.setString(1, "kfjghdksdsfsfd");
		prepareStatement.setLong(2, 1);
		prepareStatement.setLong(3, 1);
		prepareStatement.executeUpdate();
		ResultSet rs = prepareStatement.getGeneratedKeys();
		rs.next();
		long id = rs.getLong(1);
		System.out.println(id);
		connectionSettings.closeConnection();
		// connectionSettings.build();
		// String query = "select * from order_request where order_date=?";
		// PreparedStatement prepareStatement =
		// connectionSettings.getConnection().prepareStatement(query);
		// System.out.println(date);
		// prepareStatement.setDate(1, java.sql.Date.valueOf(date));
		// ResultSet resultSet = prepareStatement.executeQuery();
		// List<Order> list = new ArrayList<>();
		// if (resultSet.next()) {
		// Order order = new Order();
		// order.setId(resultSet.getLong("id"));
		// order.setTitle(resultSet.getString("title"));
		// order.setDate(resultSet.getDate("order_date"));
		// order.setStatus(resultSet.getString("status"));
		// list.add(order);
		// }
		// connectionSettings.closeConnection();
		// System.out.println(list.size());
	}
}
