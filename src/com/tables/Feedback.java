package com.tables;

public class Feedback {
	private long id;
	private long photographerId;
	private long customerId;
	private String feedback;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(long photographerId) {
		this.photographerId = photographerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
