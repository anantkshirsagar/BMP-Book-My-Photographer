package com.bmp.utils;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.dbmanager.connection.setting.ConnectionSettings;
import com.dbmanager.property.util.PropertyReader;

public class DatabaseUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtils.class);

	private DatabaseUtils() {
	}

	public static AbstractConnectionSettings getConnectionSettings(String filePath) throws IOException {
		PropertyReader propertyReader = new PropertyReader(new File(filePath));
		return new ConnectionSettings(propertyReader.propertiesReader());
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.close();
	}
}
