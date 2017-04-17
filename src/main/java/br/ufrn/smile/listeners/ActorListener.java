package br.ufrn.smile.listeners;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;

public class ActorListener extends SmileBaseListener {
	private Actor parsedActor;
	
	@Override
	public void enterActorDeclaration(SmileParser.ActorDeclarationContext context) {
		String actorName = context.actorName().getText();
		parsedActor = new Actor(actorName);
	}
	
	public Actor getParsedActor() {
		return parsedActor;
	}
}