package br.ufrn.smile.domain;

public class Dependency {
	private Actor actor;
	private String name;
	private DependencyType type;
	
	public Dependency(Actor actor, String name, String type) {
		this.actor = actor;
		this.name = name;
		this.setType(DependencyType.valueOf(type.toUpperCase()));
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public DependencyType getType() {
		return type;
	}

	public void setType(DependencyType type) {
		this.type = type;
	}

	public enum DependencyType {
		GOAL("goal"),
		RESOURCE("resource"),
		SOFTGOAL("softgoal"),
		TASK("task");
			
		private final String description;
		
		DependencyType(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
}
