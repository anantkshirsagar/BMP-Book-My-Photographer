package com.bmp.utils;

public interface AppConstants {
	public enum UserType {
		PHOTOGRAPHER, CUSTOMER, ADMIN
	}

	public enum PhotographerStatus {
		NOT_SUBMITTED, SUBMITTED, APPROVED, REJECTED
	}

	String ADMIN_USERNAME = "admin";
	String ADMIN_PASSWORD = "123456";
}
