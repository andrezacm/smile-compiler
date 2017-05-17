package br.ufrn.smile.service;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.domain.CustomWarning;

public class ErrorHandler {
	private static ErrorHandler errorHandler;
	private List<CustomError> errors;
	private List<CustomWarning> warnings;
	
	private ErrorHandler() {
		this.errors = new ArrayList<CustomError>();
		this.warnings = new ArrayList<CustomWarning>();
	}
	
	public static ErrorHandler getErrorHandler() {
		if(errorHandler == null) {
			errorHandler = new ErrorHandler();
		}
		return errorHandler;
	}
	
	public void addError(CustomError error) {
		errors.add(error);
	}
	
	public List<CustomError> getErrors() {
		return errors;
	}

	public void addWarning(CustomWarning warning) {
		warnings.add(warning);
	}

	public List<CustomWarning> getWarnings() {
		return warnings;
	}
}
