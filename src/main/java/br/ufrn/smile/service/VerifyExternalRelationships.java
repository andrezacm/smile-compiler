package br.ufrn.smile.service;

import java.util.HashMap;
import java.util.List;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.domain.Dependency;
import br.ufrn.smile.domain.Dependency.DependencyPerspective;
import br.ufrn.smile.domain.DependencyFactory;

public class VerifyExternalRelationships {
	public static void call(Actor mainActor, HashMap<String, ActorStatementFactory> actorsList) {
		mainActor.getExternalRelationships().getDependers().forEach(depender -> {
			Actor actor = VerifyActorsExistence.call(depender.getActor(), actorsList);
			
			if(actor != null) {
				DependencyPerspective perspective = DependencyFactory.getDependencyPerspective(depender.getType());
				List<Dependency> matchingDependees = actor.getExternalRelationships().getDependees(perspective);
				
				Dependency foundDependee = matchingDependees.stream().filter(dependee -> {
					return (dependee.getActor().equals(mainActor) &&
							dependee.getType().equals(depender.getType()) &&
							dependee.getName().equals(depender.getName()));
				}).findAny().orElse(null);
				
				if(foundDependee == null) {
					CustomError error = new CustomError(mainActor);
					error.setDependencyError(depender);
					
					ErrorHandler.getErrorHandler().addError(error);
				}
			}
		});
	}
}
