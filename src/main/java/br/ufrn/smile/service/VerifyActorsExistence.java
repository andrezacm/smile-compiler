package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;

public class VerifyActorsExistence {
	public static Actor call(Actor actor, HashMap<String, ActorStatementFactory> actorsList) {
		ActorStatementFactory actorSF = actorsList.get(actor.getName());
		
		if(actorSF == null) {
			CustomError error = new CustomError(actor);
			error.setMissingActorError();
			
			ErrorHandler.getErrorHandler().addError(error);
			
			return null;
		} else {
			return actorSF.getMainActor();
		}
	}
}
