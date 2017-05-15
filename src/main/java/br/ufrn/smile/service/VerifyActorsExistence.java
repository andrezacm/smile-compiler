package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;

public class VerifyActorsExistence {
	
	public static Actor call(Actor mainActor, HashMap<String, ActorStatementFactory> actorsList) {
		ActorStatementFactory actorSF = actorsList.get(mainActor.getName());
		
		if(actorSF == null) {
			//add error for missing actor
			System.out.println("missing actor");
			return null;
		} else {
			return actorSF.getMainActor();
		}
	}
}
