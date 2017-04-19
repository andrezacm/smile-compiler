package br.ufrn.smile.listeners;

import java.util.Map;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.AssociationType;

public class ActorStatementListener extends SmileBaseListener {
	private Actor mainActor;
	
	@Override
	public void enterActorStatement(SmileParser.ActorStatementContext context) {
		ActorListener actorListener = new ActorListener();
		context.actorDeclaration().enterRule(actorListener);
		
		mainActor = actorListener.getParsedActor();
		
		ActorAssociationListener actorAssociationListener = new ActorAssociationListener();
		context.actorAssociationDeclaration().forEach(association -> association.enterRule(actorAssociationListener));
		Map<AssociationType, Actor> actorAssociatons = actorAssociationListener.getActorAssociatons();
		
		mainActor.setActorAssociations(actorAssociatons);
	}
	
	public Actor getMainActor() {
		return mainActor;
	}
}