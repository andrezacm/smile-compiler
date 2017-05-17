package br.ufrn.smile.service;

import java.util.HashMap;

import br.ufrn.smile.domain.Actor;

public class ActorsReferenceHandler {
	private static ActorsReferenceHandler actorsHandler;
	private HashMap<String, Actor> actors;
	
	private ActorsReferenceHandler() {
		this.actors = new HashMap<String, Actor>();
	}
	
	public static ActorsReferenceHandler getActorsReferenceHandler() {
		if(actorsHandler == null) {
			actorsHandler = new ActorsReferenceHandler();
		}
		
		return actorsHandler;
	}

	public Actor getActor(String actorName) {
		return actors.get(actorName);
	}

	public void addActor(Actor actor) {
		this.actors.put(actor.getName(), actor);
	}
}
