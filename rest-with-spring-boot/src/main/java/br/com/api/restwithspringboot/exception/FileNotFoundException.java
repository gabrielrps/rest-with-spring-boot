package br.com.api.restwithspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -532654842442554968L;

	public FileNotFoundException(String exception) {
		super(exception);
	}
	
	public FileNotFoundException(String exception, Throwable cause) {
		super(exception, cause);
	}

}