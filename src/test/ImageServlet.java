package test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmp.utils.ConnectionUtils;
import com.dbmanager.connection.setting.AbstractConnectionSettings;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String photoId = request.getParameter("photoId");
			AbstractConnectionSettings connectionSettings = ConnectionUtils.getConnectionSettings();
			connectionSettings.build();
			String query = "select * from photo where id=" + photoId;
			PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			byte[] bytes = null;
			if (resultSet.next()) {
				bytes = resultSet.getBytes("photo");
				ServletOutputStream outputStream = response.getOutputStream();
				byte[] encode = Base64.getEncoder().encode(bytes);
				outputStream.write(encode, 0, encode.length);
			}
			connectionSettings.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
