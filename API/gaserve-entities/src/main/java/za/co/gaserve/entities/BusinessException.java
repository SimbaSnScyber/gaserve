package za.co.gaserve.entities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.co.gaserve.entities.lists.UserStatus;


public class BusinessException extends RuntimeException {

	private HttpStatus statusCode;

	private UserStatus userStatus;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public BusinessException(String message,HttpStatus statusCode) {
		super(message);
		this.statusCode = statusCode;

	}

	public BusinessException(Throwable cause,HttpStatus statusCode) {
		super(cause);
		this.statusCode = statusCode;
	}

	public BusinessException(String message, Throwable cause,HttpStatus statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,HttpStatus statusCode) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
}
