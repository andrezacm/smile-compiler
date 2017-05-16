package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;

public class VerifyAssociations {
	public static void call(Actor mainActor, HashMap<String, ActorStatementFactory> actorsList, String fileName) {
		mainActor.getAssociations().forEach(association -> {
			VerifyActorsExistence.call(association.getActor(), actorsList, fileName);
			try {				
				if (!association.isValid(mainActor)) {
					CustomError error = CustomError.CustomErrorFromFileName(fileName);
					error.setAssociationError(association);
					
					ErrorHandler.getErrorHandler().addError(error);
				}
			} catch (Exception e1) {
				ErrorHandler.getErrorHandler().addError(CustomError.CustomErrorFromMessage(e1.getMessage()));
			}
		});
	}
}
