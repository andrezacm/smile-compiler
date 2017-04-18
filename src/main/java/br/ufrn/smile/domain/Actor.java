package br.ufrn.smile.domain;

public class Actor {
	private String name;
	private ActorType actorType;
	
	public Actor(String name, String actorType) {
		this.name = name;
		this.actorType = ActorType.valueOf(actorType.toUpperCase());
	}
	
	public String getName() {
		return name;
	}
	
	public String getActorType() {
		return actorType.getDescription();
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