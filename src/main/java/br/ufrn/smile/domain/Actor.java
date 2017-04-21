package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

public class Actor {
	private String name;
	private ActorType actorType;
	private List<Association> actorAssociatons;
	
	public Actor(String name, String actorType) {
		this.name = name;
		this.actorType = ActorType.valueOf(actorType.toUpperCase());
		this.actorAssociatons = new ArrayList<Association>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getActorType() {
		return actorType.getDescription();
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