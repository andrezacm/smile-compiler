package br.ufrn.smile.listeners;

import java.util.List;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class ActorStatementListener extends SmileBaseListener {
	private Actor mainActor;
	
	@Override
	public void enterActorStatement(SmileParser.ActorStatementContext context) {
		ActorListener actorListener = new ActorListener();
		context.actorDeclaration().enterRule(actorListener);
		
		mainActor = actorListener.getParsedActor();
		
		AssociationListener associationListener = new AssociationListener();
		context.associationDeclaration().forEach(association -> association.enterRule(associationListener));
		List<Association> associatons = associationListener.getActorAssociatons();
		
		mainActor.setAssociations(associatons);
		
		ExternalRelationshipsListener externalReListener = new ExternalRelationshipsListener();
		context.externalRelationships().enterRule(externalReListener);
		
		mainActor.setExternalRelationships(externalReListener.getExternalRelationships());
	}
	
	public Actor getMainActor() {
		return mainActor;
	}
}