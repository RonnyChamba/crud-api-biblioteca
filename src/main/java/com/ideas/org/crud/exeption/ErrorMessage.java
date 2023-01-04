package com.ideas.org.crud.exeption;


import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

	private final String error;
	private final Object message;
	private final Integer code;


	public ErrorMessage(Exception exception, Integer code) {
		this.error = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.code = code;
	}

	public ErrorMessage(Exception exception, Map<String, String> data, Integer code) {
		this.error = exception.getClass().getSimpleName();
		this.message = data;
		this.code = code;
	}
}

