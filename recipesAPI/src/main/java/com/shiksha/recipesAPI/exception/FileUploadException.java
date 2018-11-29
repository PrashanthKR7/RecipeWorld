package com.shiksha.recipesAPI.exception;

public class FileUploadException extends RuntimeException {
	
	private static final long serialVersionUID = 138841373113226835L;

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

}
