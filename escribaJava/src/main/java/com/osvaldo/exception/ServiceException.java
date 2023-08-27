package com.osvaldo.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;

	public ServiceException() {
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ServiceException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
