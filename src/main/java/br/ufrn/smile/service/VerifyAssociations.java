package br.ufrn.smile.service;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.CustomError;

public class VerifyAssociations {
	
	public static void call(Actor mainActor) {
		mainActor.getActorAssociatons().forEach(association -> {
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
