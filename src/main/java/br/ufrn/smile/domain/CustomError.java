package br.ufrn.smile.domain;

public class CustomError {
	private String message;
	
	public CustomError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
