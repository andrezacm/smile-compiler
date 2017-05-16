package br.ufrn.smile.domain;

public class Dependency {
	private Actor actor;
	private String name;
	private DependencyType type;
	private DependencyPerspective perspective;
	private Position position;
	
	public Dependency(Actor actor, String name, String type, String perspective) {
		this.actor = actor;
		this.name = name;
		this.type = DependencyType.valueOf(type.toUpperCase());
		this.perspective = DependencyPerspective.valueOf(perspective.toUpperCase());
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

	public DependencyPerspective getPerspective() {
		return perspective;
	}

	public void setPerspective(DependencyPerspective perspective) {
		this.perspective = perspective;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(int startCharPosition, int startInLine, int stopCharPosition, int stopInLine) {
		this.position = new Position(startCharPosition, startInLine, stopCharPosition, stopInLine);
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
	
	public enum DependencyPerspective {
		CARRIESOUT("carriesOut"),
		DEPENDSON("dependsOn"),
		PROVIDES("provides"),
		REACHES("reaches");
		
		private final String description;
		
		DependencyPerspective(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
}
