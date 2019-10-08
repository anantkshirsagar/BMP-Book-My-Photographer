package com.bmp.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.dbmanager.connection.setting.ConnectionSettings;
import com.dbmanager.property.util.PropertyReader;
import com.servlet.RegistrationServlet;

public class ConnectionUtils {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServlet.class);
	private static final String PROPERTIES = "S:\\suyog\\NSG 2019\\BMP-Book-My-Photographer\\src\\mysql.properties";
	private static AbstractConnectionSettings connectionSettings;

	private ConnectionUtils() {
	}

	static {
		try {
			PropertyReader propertyReader = new PropertyReader(new File(PROPERTIES));
			connectionSettings = new ConnectionSettings(propertyReader.propertiesReader());
		} catch (IOException e) {
			LOG.error("Exception {}", e);
		}
	}

	public static AbstractConnectionSettings getConnectionSettings() throws IOException {
		return connectionSettings == null
				? new ConnectionSettings(new PropertyReader(new File(PROPERTIES)).propertiesReader())
				: connectionSettings;
	}
}
