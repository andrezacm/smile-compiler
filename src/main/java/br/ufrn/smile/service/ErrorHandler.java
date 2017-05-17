package br.ufrn.smile.service;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.domain.CustomWarning;

public class ErrorHandler {
	private static final ErrorHandler errorHandler = new ErrorHandler();
	private List<CustomError> errors = new ArrayList<CustomError>();
	private List<CustomWarning> warnings = new ArrayList<CustomWarning>();
	
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

	public void addWarning(CustomWarning warning) {
		warnings.add(warning);
	}

	public List<CustomWarning> getWarnings() {
		return warnings;
	}
}
