package com.ideas.org.crud.exeption;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "";

	public UnauthorizedException(String detail) {
		super(DESCRIPTION + "" + detail);
	}

}
