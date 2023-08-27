package com.osvaldo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespostaException {
	private String message;
	private HttpStatus httpStatus;
	private Integer code;
	private String type;
	private String title = "Título";
	private String body;
	ResponseEntity<?> etity = null;

	public RespostaException() {
	}

	public RespostaException(String message, HttpStatus httpStatus) {
		super();
		this.message = message != null ? message : "";
		this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.ACCEPTED;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ResponseEntity<?> getEtity() {
		return etity;
	}

	public void setEtity(ResponseEntity<?> etity) {
		this.etity = etity;
	}

}
