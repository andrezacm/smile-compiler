package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
	private static final ErrorHandler errorHandler = new ErrorHandler();
	private static List<CustomError> errors = new ArrayList<CustomError>();
	
	private ErrorHandler() { }
	
	public static ErrorHandler getErrorHandler() {
		return errorHandler;
	}
	
	public void addError(CustomError error) {
		errors.add(error);
	}
	
	public List<CustomError> getErrors() {
		return errors;
	}
}
