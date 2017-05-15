package br.ufrn.smile.service;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.domain.CustomError;

public class ErrorHandler {
	private static final ErrorHandler errorHandler = new ErrorHandler();
	private List<CustomError> errors = new ArrayList<CustomError>();
	
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
