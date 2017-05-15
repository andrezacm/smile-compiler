package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;

public class VerifyAssociations {
	
	public static void call(Actor mainActor, HashMap<String, ActorStatementFactory> actorsList) {
		mainActor.getAssociations().forEach(association -> {
			VerifyActorsExistence.call(association.getActor(), actorsList);
			
			try {				
				if (!association.isValid(mainActor)) {
					ErrorHandler.getErrorHandler().addError(new CustomError(mainActor, association));
				}
			} catch (Exception e1) {
				ErrorHandler.getErrorHandler().addError(new CustomError(e1.getMessage()));
			}
		});
	}
}
