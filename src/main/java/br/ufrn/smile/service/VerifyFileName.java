package br.ufrn.smile.service;

import br.ufrn.smile.domain.CustomError;

public class VerifyFileName {
	public static void call(String actorName, String fileName) {
		if(actorName != fileName) {
			CustomError error = new CustomError();
			error.setFileNameError(actorName, fileName);
			
			ErrorHandler.getErrorHandler().addError(error);
		}
	}
}
