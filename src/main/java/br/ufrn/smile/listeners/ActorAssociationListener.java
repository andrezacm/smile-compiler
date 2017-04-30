package br.ufrn.smile.listeners;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Association;

public class ActorAssociationListener extends SmileBaseListener {
	private List<Association> actorAssociatons;
	
	public ActorAssociationListener() {
		actorAssociatons = new ArrayList<Association>();
	}
	
	@Override 
	public void enterActorAssociationDeclaration(SmileParser.ActorAssociationDeclarationContext context) {
		String associationType = context.associationType().getText();
		Association association = new Association(associationType);
		
		List<ActorListener> actorListeners = new ArrayList<ActorListener>();
		
		context.actorDeclaration().forEach(actor -> {
			ActorListener actorListener = new ActorListener();
			actor.enterRule(actorListener);
			actorListeners.add(actorListener);
		});
		
		List<Actor> actors = new ArrayList<Actor>();
		
		actorListeners.forEach(listener -> actors.add(listener.getParsedActor()));
		
		association.setActors(actors);
		
		actorAssociatons.add(association);
	}
	
	public List<Association> getActorAssociatons() {
		return actorAssociatons;
	}
}