package test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;

public class LocalDateTest {
	public static void main(String[] args) throws IOException, SQLException {
		AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
		String query = "insert into test(id,date) values (?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setInt(1, 1);
		prepareStatement.setDate(2, null);
		prepareStatement.executeUpdate();
		connectionSettings.closeConnection();
	}
}
