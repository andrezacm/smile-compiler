package br.ufrn.smile.listeners;

import java.util.HashMap;
import java.util.Map;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.AssociationType;

public class ActorAssociationListener extends SmileBaseListener {
	private Map<AssociationType, Actor> actorAssociatons;
	
	public ActorAssociationListener() {
		actorAssociatons = new HashMap<AssociationType, Actor>();
	}
	
	@Override 
	public void enterActorAssociationDeclaration(SmileParser.ActorAssociationDeclarationContext context) {
		String association = context.associationType().getText();
		AssociationType associationType = AssociationType.valueOf(association.toUpperCase());
		
		ActorListener actorListener = new ActorListener();
		context.actorDeclaration().enterRule(actorListener);
		Actor actor = actorListener.getParsedActor();
		
		actorAssociatons.put(associationType, actor);
	}
	
	public Map<AssociationType, Actor> getActorAssociatons() {
		return actorAssociatons;
	}
}