package com.bmp.utils;

import java.io.File;

public interface AppConstants {
	public enum UserType {
		PHOTOGRAPHER, CUSTOMER, ADMIN
	}

	public enum PhotographerStatus {
		NOT_SUBMITTED, SUBMITTED, APPROVED, REJECTED
	}

	public enum OrderStatus {
		SUBMITTED, APPROVED, REJECTED, CANCELED, COMPLETED
	}

	String ADMIN_USERNAME = "admin";
	String ADMIN_PASSWORD = "123456";
	
	
	public static final String DATABASE_CONFIG_PATH = "app-configurations.properties";
	public static final String CREATE_MYSQL_TABLES = "resources" + File.separator + "database" + File.separator
			+ "mysql_tables.sql";

	public static final String CREATE_PSQL_TABLES = "resources" + File.separator + "database" + File.separator
			+ "psql_tables.sql";

	public enum DATABASE_TYPE {
		MYSQL, PSQL
	}

	public static final String APP_FOLDER_NAME = "book_my_photographer";
}
