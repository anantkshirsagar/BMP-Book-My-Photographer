package com.bmp.utils;

public interface AppConstants {
	public enum UserType {
		PHOTOGRAPHER, CUSTOMER, ADMIN
	}

	public enum PhotographerStatus {
		NOT_SUBMITTED, SUBMITTED, APPROVED, REJECTED
	}

	public enum OrderStatus {
		DRAFT, SUBMITTED, APPROVED, REJECTED, CANCELED
	}

	String ADMIN_USERNAME = "admin";
	String ADMIN_PASSWORD = "123456";
}
