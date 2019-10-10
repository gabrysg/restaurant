package com.restaurant.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RestaurantException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	HttpStatus status = HttpStatus.BAD_REQUEST;

	public RestaurantException(String message) {
		super(message);
	}

	public RestaurantException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
