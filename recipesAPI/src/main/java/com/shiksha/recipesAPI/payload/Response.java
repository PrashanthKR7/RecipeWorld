package com.shiksha.recipesAPI.payload;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Response implements Serializable, Cloneable {

	private static final long serialVersionUID = -3773253896160786443L;
	private Instant timestamp;
	private int status;
	private String error;
	private String success;
	private List<String> errors;
	private String message;
	private String path;
	private Object result;

	public Response() {
		super();
	}

	public Response(int status, String error, String message, String path, Object result) {
		super();
		this.timestamp = new Date().toInstant();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.result = result;
	}

	public Response(int status, String error, String message, String path, Object result, String success) {
		super();
		this.timestamp = new Date().toInstant();
		this.status = status;
		this.error = error;
		this.success = success;
		this.message = message;
		this.path = path;
		this.result = result;
	}

	public Response(int status, String error, List<String> errors, String message, String path, Object result) {
		super();
		this.timestamp = new Date().toInstant();
		this.status = status;
		this.error = error;
		this.setErrors(errors);
		this.message = message;
		this.path = path;
		this.result = result;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public Response clone(Response response) {
		try {
			return (Response) response.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}