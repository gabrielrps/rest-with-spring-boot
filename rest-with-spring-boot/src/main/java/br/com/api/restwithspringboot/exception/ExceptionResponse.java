package br.com.api.restwithspringboot.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 247278953605193599L;
	
	private LocalDateTime date;
	private String message;
	private String details;
	
	public ExceptionResponse(LocalDateTime date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
