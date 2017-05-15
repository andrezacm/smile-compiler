package br.ufrn.smile.listeners;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.service.IDHandler;

public class ActorListener extends SmileBaseListener {
	private Actor parsedActor;
	
	@Override
	public void enterActorDeclaration(SmileParser.ActorDeclarationContext context) {
		int id = new IDHandler().getID();
		String actorName = context.actorName().getText();
		String actorType = context.actorType().getText();
		
		parsedActor = new Actor(id, actorName, actorType);
	}
	
	public Actor getParsedActor() {
		return parsedActor;
	}
}