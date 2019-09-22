package com.bmp.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
	private static final String APPLICATION_JSON = "application/json";
	private static final String UTF_8 = "UTF-8";
	
	private ResponseUtils(){
		
	}
	
	public static void setResponseParameter(HttpServletResponse response){
		response.setContentType(APPLICATION_JSON);
		response.setCharacterEncoding(UTF_8);
	}
}
