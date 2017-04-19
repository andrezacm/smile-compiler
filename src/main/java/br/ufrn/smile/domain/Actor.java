package br.ufrn.smile.domain;

import java.util.HashMap;
import java.util.Map;

public class Actor {
	private String name;
	private ActorType actorType;
	private Map<AssociationType, Actor> actorAssociatons;
	
	public Actor(String name, String actorType) {
		this.name = name;
		this.actorType = ActorType.valueOf(actorType.toUpperCase());
		this.actorAssociatons = new HashMap<AssociationType, Actor>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getActorType() {
		return actorType.getDescription();
	}
	
	public Map<AssociationType, Actor> getActorAssociatons() {
		return actorAssociatons;
	}
	
	public void setActorAssociations(Map<AssociationType, Actor> actorAssociatons) {
		this.actorAssociatons = actorAssociatons;
	}

	public enum ActorType {
		ACTOR("actor"), 
		AGENT("agent"), 
		POSITION("position"), 
		ROLE("role");
		
		private final String description;
		
		ActorType(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
}