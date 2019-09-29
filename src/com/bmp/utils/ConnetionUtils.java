package com.bmp.utils;

import java.io.File;
import java.io.IOException;

import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.dbmanager.connection.setting.ConnectionSettings;
import com.dbmanager.property.util.PropertyReader;

public class ConnetionUtils {
	private static AbstractConnectionSettings connectionSettings;
	private final static String PROPERTIES = "S:\\suyog\\NSG 2019\\BMP-Book-My-Photographer\\resources\\mysql.properties";
	static {
		try {
			connectionSettings = new ConnectionSettings(new PropertyReader(new File(PROPERTIES)).propertiesReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AbstractConnectionSettings getConnectionSettings() throws IOException {
		return connectionSettings == null
				? new ConnectionSettings(new PropertyReader(new File(PROPERTIES)).propertiesReader())
				: connectionSettings;
	}
}
