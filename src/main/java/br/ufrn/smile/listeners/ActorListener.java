package br.ufrn.smile.listeners;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.service.ActorsReferenceHandler;
import br.ufrn.smile.service.IDHandler;

public class ActorListener extends SmileBaseListener {
	private Actor parsedActor;
	
	@Override
	public void enterActorDeclaration(SmileParser.ActorDeclarationContext context) {
		ActorsReferenceHandler actorsHandler = ActorsReferenceHandler.getActorsReferenceHandler();
		
		String actorName = context.actorName().getText();
		
		Actor actor = actorsHandler.getActor(actorName);
		
		if(actor == null) {
			int id = new IDHandler().getID();
			String actorType = context.actorType().getText();
			
			parsedActor = new Actor(id, actorName, actorType);
			
			parsedActor.setPosition(context.start.getCharPositionInLine(), 
									context.start.getLine(), 
									context.stop.getCharPositionInLine(), 
									context.stop.getLine());
			
			actorsHandler.addActor(parsedActor);
		} else {
			parsedActor = actor;
		}
	}
	
	public Actor getParsedActor() {
		return parsedActor;
	}
}