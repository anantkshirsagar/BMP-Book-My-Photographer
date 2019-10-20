package com.bmp.utils;

import javax.servlet.http.HttpServletResponse;

public class UtilService {
	private static final String APPLICATION_JSON = "application/json";
	private static final String UTF_8 = "UTF-8";

	private UtilService() {

	}

	public static void setResponseParameter(HttpServletResponse response) {
		response.setContentType(APPLICATION_JSON);
		response.setCharacterEncoding(UTF_8);
	}

	public static java.sql.Date getSQLDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Time getSQLTime(java.util.Date date) {
		return new java.sql.Time(date.getTime());
	}
}
