package com.shiksha.recipesAPI.payload;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	private static final long serialVersionUID = -3773253896160786443L;

}