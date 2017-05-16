package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;

public class VerifyActorsExistence {
	public static Actor call(Actor actor, HashMap<String, ActorStatementFactory> actorsList, String fileName) {
		ActorStatementFactory actorSF = actorsList.get(actor.getName());
		
		if(actorSF == null) {
			CustomError error = CustomError.CustomErrorFromFileName(fileName);
			error.setMissingActorError(actor);
			
			ErrorHandler.getErrorHandler().addError(error);
			
			return null;
		} else {
			return actorSF.getMainActor();
		}
	}
}
