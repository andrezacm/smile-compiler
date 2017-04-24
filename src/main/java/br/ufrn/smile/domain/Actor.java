package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

public class Actor {
	private String name;
	private ActorType type;
	private List<Association> actorAssociatons;
	
	public Actor(String name, String type) {
		this.name = name;
		this.type = ActorType.valueOf(type.toUpperCase());
		this.actorAssociatons = new ArrayList<Association>();
	}
	
	public String getName() {
		return name;
	}
	
	public ActorType getType() {
		return type;
	}
	
	public List<Association> getActorAssociatons() {
		return actorAssociatons;
	}
	
	public void setActorAssociations(List<Association> actorAssociatons) {
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