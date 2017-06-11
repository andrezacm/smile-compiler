package br.ufrn.smile.service;

import br.ufrn.smile.domain.CustomError;

public class VerifyFileName {
	public static void call(String actorName, String fileName) {
		if(!actorName.equals(fileName)) {
			CustomError error = CustomError.CustomErrorFromFileName(fileName);
			error.setFileNameError(actorName);
			
			ErrorHandler.getErrorHandler().addError(error);
		}
	}
}
