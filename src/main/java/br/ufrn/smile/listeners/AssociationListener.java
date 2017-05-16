package br.ufrn.smile.listeners;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.*;
import br.ufrn.smile.domain.Association;

public class AssociationListener extends SmileBaseListener {
	private List<Association> associations;
	
	public AssociationListener() {
		associations = new ArrayList<Association>();
	}
	
	@Override 
	public void enterAssociationDeclaration(SmileParser.AssociationDeclarationContext context) {
		String associationType = context.associationType().getText();
		
		List<ActorListener> actorListeners = new ArrayList<ActorListener>();
		
		context.actorDeclaration().forEach(actor -> {
			ActorListener actorListener = new ActorListener();
			actor.enterRule(actorListener);
			actorListeners.add(actorListener);
		});
		
		actorListeners.forEach(listener -> {
			Association association = new Association(associationType, listener.getParsedActor());
			association.setPosition(context.start.getCharPositionInLine(), 
									context.start.getLine(), 
									context.stop.getCharPositionInLine(), 
									context.stop.getLine());

			associations.add(association);
		});
	}
	
	public List<Association> getActorAssociatons() {
		return associations;
	}
}